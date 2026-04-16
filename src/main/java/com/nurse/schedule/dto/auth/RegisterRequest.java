package com.nurse.schedule.dto.auth;

public record RegisterRequest(
        Long AUTH_SEQ,

        // TB_USER_INFO
        String USER_NM,
        String PHONE_NUM,
        String BIRTH_DATE, // YYYY-MM-DD
        String GENDER, // M/F
        String USER_CI,
        String USER_DI,

        // TB_USER_ACCOUNT
        String LOGIN_ID,
        String LOGIN_PW,

        // TB_USER_TERMS_AGREE
        String SERVICE_TERMS_YN,
        String PRIVACY_POLICY_YN,
        String MARKETING_RECV_YN
) {
}

