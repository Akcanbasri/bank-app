package com.banking.core.crosscuttingconcerns.exceptions.problemdetails;

import org.springframework.http.HttpStatus;

public class InternalServerErrorProblemDetails extends ProblemDetails {
    public InternalServerErrorProblemDetails() {
        setTitle("Internal Server Error");
        setType("https://banking.com/exceptions/internal");
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        setDetail("An internal server error occurred");
    }
} 