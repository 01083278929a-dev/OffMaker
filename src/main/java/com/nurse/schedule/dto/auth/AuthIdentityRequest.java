package com.nurse.schedule.dto.auth;

public record AuthIdentityRequest(
        String USER_NM,
        String PHONE_NUM
) {
}

