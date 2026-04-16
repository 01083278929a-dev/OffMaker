package com.nurse.schedule.mapper.auth;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthMapper {

    int InsertAuthHistory(AuthHistoryRow row);

    AuthHistoryRow SelectAuthHistoryBySeq(@Param("AUTH_SEQ") Long authSeq);

    AuthHistoryRow SelectLatestSuccessAuthHistory(@Param("USER_NM") String userNm,
                                                  @Param("PHONE_NUM") String phoneNum);

    int InsertUserInfo(UserInfoRow row);

    int InsertUserAccount(UserAccountRow row);

    int InsertUserTermsAgree(UserTermsAgreeRow row);

    int InsertVacationMgmt(VacationMgmtRow row);

    int InsertDutyTypes(@Param("list") List<DutyTypeRow> rows);
}

