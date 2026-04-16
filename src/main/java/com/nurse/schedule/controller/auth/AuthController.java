package com.nurse.schedule.controller.auth;

import com.nurse.schedule.dto.auth.AuthIdentityRequest;
import com.nurse.schedule.dto.auth.AuthIdentityResponse;
import com.nurse.schedule.dto.auth.RegisterRequest;
import com.nurse.schedule.dto.auth.RegisterResponse;
import com.nurse.schedule.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/identity")
    public ResponseEntity<AuthIdentityResponse> CreateIdentityAuth(@RequestBody AuthIdentityRequest request) {
        return ResponseEntity.ok(authService.CreateIdentityAuth(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> Register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.Register(request));
    }
}

