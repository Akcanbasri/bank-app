package com.banking.core.crosscuttingconcerns.exceptions.problemdetails;

import org.springframework.http.HttpStatus;

public class AuthorizationProblemDetails extends ProblemDetails {
    public AuthorizationProblemDetails() {
        setTitle("Authorization Error");
        setType("https://banking.com/exceptions/authorization");
        setStatus(HttpStatus.UNAUTHORIZED.value());
        setDetail("You are not authorized to perform this operation");
    }
} 