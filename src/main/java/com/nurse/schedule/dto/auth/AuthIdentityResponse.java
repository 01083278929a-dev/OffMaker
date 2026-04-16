package com.nurse.schedule.dto.auth;

public record AuthIdentityResponse(
        long AUTH_SEQ,
        String USER_CI,
        String USER_DI
) {
}

