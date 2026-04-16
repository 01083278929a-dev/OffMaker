package com.nurse.schedule.service.auth;

import com.nurse.schedule.dto.auth.AuthIdentityRequest;
import com.nurse.schedule.dto.auth.AuthIdentityResponse;
import com.nurse.schedule.dto.auth.RegisterRequest;
import com.nurse.schedule.dto.auth.RegisterResponse;
import com.nurse.schedule.mapper.auth.AuthMapper;
import com.nurse.schedule.mapper.auth.AuthHistoryRow;
import com.nurse.schedule.mapper.auth.DutyTypeRow;
import com.nurse.schedule.mapper.auth.UserAccountRow;
import com.nurse.schedule.mapper.auth.UserInfoRow;
import com.nurse.schedule.mapper.auth.UserTermsAgreeRow;
import com.nurse.schedule.mapper.auth.VacationMgmtRow;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthService {

    private final AuthMapper authMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    /**
     * 본인인증(샘플 구현): TB_AUTH_HISTORY에 성공 이력을 1건 생성한다.
     * 실제 SMS/대행사 연동 시에는 요청/검증 단계를 분리하고 AUTH_STATUS를 갱신하도록 확장한다.
     */
    @Transactional
    public AuthIdentityResponse CreateIdentityAuth(AuthIdentityRequest request) {
        final String userNm = SafeTrim(request.USER_NM());
        final String phoneNum = SafeTrim(request.PHONE_NUM());
        if (userNm.isEmpty() || phoneNum.isEmpty()) {
            throw new IllegalArgumentException("USER_NM/PHONE_NUM은 필수입니다.");
        }

        final String authCode = String.format("%06d", (int) (Math.random() * 1_000_000));
        final String txId = UUID.randomUUID().toString();
        final AuthHistoryRow row = new AuthHistoryRow(null, userNm, phoneNum, authCode, txId, "1");
        authMapper.InsertAuthHistory(row);

        // NOTE: TB_AUTH_HISTORY에는 CI/DI 컬럼이 없으므로 샘플 값 반환(추후 본인인증 연동 시 실제 값으로 대체)
        return new AuthIdentityResponse(Objects.requireNonNull(row.getAUTH_SEQ()), "CI_PLACEHOLDER", "DI_PLACEHOLDER");
    }

    @Transactional
    public RegisterResponse Register(RegisterRequest request) {
        ValidateRegisterRequest(request);

        // 본인인증 이력 최종 검증
        // - 입력한 성명/번호에 대한 "최신 성공 기록"을 확인
        // - 그 기록의 AUTH_SEQ가 요청의 AUTH_SEQ와 일치해야 함
        AuthHistoryRow latest = authMapper.SelectLatestSuccessAuthHistory(
                SafeTrim(request.USER_NM()),
                SafeTrim(request.PHONE_NUM())
        );
        if (latest == null) {
            throw new IllegalArgumentException("본인인증 성공 이력이 없습니다. 본인인증을 먼저 완료해 주세요.");
        }
        if (!Objects.equals(latest.getAUTH_SEQ(), request.AUTH_SEQ())) {
            throw new IllegalArgumentException("본인인증 이력이 최신 상태가 아닙니다. 다시 본인인증을 진행해 주세요.");
        }

        try {
            // 1) TB_USER_INFO
            UserInfoRow userInfo = new UserInfoRow(
                    null,
                    request.USER_NM(),
                    request.PHONE_NUM(),
                    NullIfBlank(request.USER_CI()),
                    NullIfBlank(request.USER_DI()),
                    ParseDateOrNull(request.BIRTH_DATE()),
                    request.GENDER()
            );
            authMapper.InsertUserInfo(userInfo);
            long userSeq = Objects.requireNonNull(userInfo.getUSER_SEQ());

            // 2) TB_USER_ACCOUNT (BCrypt)
            final String hashedPw = passwordEncoder.encode(request.LOGIN_PW());
            authMapper.InsertUserAccount(new UserAccountRow(null, userSeq, request.LOGIN_ID(), hashedPw));

            // 3) TB_USER_TERMS_AGREE
            authMapper.InsertUserTermsAgree(new UserTermsAgreeRow(
                    null,
                    userSeq,
                    request.SERVICE_TERMS_YN(),
                    request.PRIVACY_POLICY_YN(),
                    request.MARKETING_RECV_YN()
            ));

            // 4) Default setup: TB_SET_VACATION_MGMT
            final String baseYear = Year.now().toString();
            authMapper.InsertVacationMgmt(new VacationMgmtRow(null, userSeq, baseYear, 0.0, 0.0, 0.0));

            // 5) Default setup: TB_SET_DUTY_TYPE (D/E/N/Off)
            List<DutyTypeRow> dutyTypes = List.of(
                    new DutyTypeRow(null, userSeq, "D", null, null, "#568cff", "WORK", "N", "Y"),
                    new DutyTypeRow(null, userSeq, "E", null, null, "#749eff", "WORK", "N", "Y"),
                    new DutyTypeRow(null, userSeq, "N", null, null, "#004aae", "WORK", "N", "Y"),
                    new DutyTypeRow(null, userSeq, "Off", null, null, "#ff9473", "OFF", "N", "Y")
            );
            authMapper.InsertDutyTypes(dutyTypes);

            return new RegisterResponse(userSeq);
        } catch (DuplicateKeyException dup) {
            throw new IllegalArgumentException("이미 가입된 휴대폰 번호이거나 중복된 데이터가 존재합니다.");
        }
    }

    private static void ValidateRegisterRequest(RegisterRequest request) {
        if (request.AUTH_SEQ() == null || request.AUTH_SEQ() <= 0) {
            throw new IllegalArgumentException("AUTH_SEQ는 필수입니다.");
        }
        if (SafeTrim(request.USER_NM()).isEmpty()) throw new IllegalArgumentException("USER_NM은 필수입니다.");
        if (SafeTrim(request.PHONE_NUM()).isEmpty()) throw new IllegalArgumentException("PHONE_NUM은 필수입니다.");
        if (SafeTrim(request.BIRTH_DATE()).isEmpty()) throw new IllegalArgumentException("BIRTH_DATE는 필수입니다.");
        if (!"M".equals(request.GENDER()) && !"F".equals(request.GENDER())) {
            throw new IllegalArgumentException("GENDER는 M/F만 가능합니다.");
        }
        if (SafeTrim(request.LOGIN_ID()).isEmpty()) throw new IllegalArgumentException("LOGIN_ID는 필수입니다.");
        if (SafeTrim(request.LOGIN_PW()).length() < 8) throw new IllegalArgumentException("LOGIN_PW는 8자 이상이어야 합니다.");
        if (!"Y".equals(request.SERVICE_TERMS_YN()) || !"Y".equals(request.PRIVACY_POLICY_YN())) {
            throw new IllegalArgumentException("필수 약관 동의가 필요합니다.");
        }
        if (!"Y".equals(request.MARKETING_RECV_YN()) && !"N".equals(request.MARKETING_RECV_YN())) {
            throw new IllegalArgumentException("MARKETING_RECV_YN은 Y/N만 가능합니다.");
        }
    }

    private static String SafeTrim(String s) {
        return s == null ? "" : s.trim();
    }

    private static String NullIfBlank(String s) {
        String t = SafeTrim(s);
        return t.isEmpty() ? null : t;
    }

    private static LocalDate ParseDateOrNull(String iso) {
        try {
            String t = SafeTrim(iso);
            if (t.isEmpty()) return null;
            return LocalDate.parse(t);
        } catch (Exception e) {
            throw new IllegalArgumentException("BIRTH_DATE 형식이 올바르지 않습니다. (YYYY-MM-DD)");
        }
    }
}

