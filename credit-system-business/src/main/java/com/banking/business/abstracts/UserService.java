package com.banking.business.abstracts;

import com.banking.business.dtos.requests.LoginRequest;
import com.banking.business.dtos.requests.RegisterRequest;
import com.banking.business.dtos.responses.TokenResponse;
import com.banking.entities.User;

public interface UserService {
    TokenResponse register(RegisterRequest request);

    TokenResponse login(LoginRequest request);

    User findByUsername(String username);

    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}