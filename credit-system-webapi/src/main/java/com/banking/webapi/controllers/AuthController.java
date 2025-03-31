package com.banking.webapi.controllers;

import com.banking.business.abstracts.UserService;
import com.banking.business.dtos.requests.LoginRequest;
import com.banking.business.dtos.requests.RegisterRequest;
import com.banking.business.dtos.responses.TokenResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        TokenResponse tokenResponse = userService.register(registerRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = userService.login(loginRequest);
        return ResponseEntity.ok(tokenResponse);
    }
}