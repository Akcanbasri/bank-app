package com.banking.core.crosscuttingconcerns.exceptions.problemdetails;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ValidationProblemDetails extends ProblemDetails {
    public ValidationProblemDetails(Map<String, String> validationErrors) {
        setTitle("Validation Rule Violation");
        setType("https://banking.com/exceptions/validation");
        setStatus(HttpStatus.BAD_REQUEST.value());
        setDetail("Validation errors occurred");
        setExtensions(Map.of("validationErrors", validationErrors));
    }
}