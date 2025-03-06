package com.banking.core.crosscuttingconcerns.exceptions.problemdetails;

import org.springframework.http.HttpStatus;

public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails(String detail) {
        setTitle("Business Rule Violation");
        setDetail(detail);
        setType("https://banking.com/exceptions/business");
        setStatus(HttpStatus.BAD_REQUEST.value());
    }
} 