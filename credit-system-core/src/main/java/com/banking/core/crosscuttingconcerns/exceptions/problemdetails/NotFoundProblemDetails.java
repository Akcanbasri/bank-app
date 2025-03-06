package com.banking.core.crosscuttingconcerns.exceptions.problemdetails;

import org.springframework.http.HttpStatus;

public class NotFoundProblemDetails extends ProblemDetails {
    public NotFoundProblemDetails(String detail) {
        setTitle("Resource Not Found");
        setType("https://banking.com/exceptions/not-found");
        setStatus(HttpStatus.NOT_FOUND.value());
        setDetail(detail);
    }
} 