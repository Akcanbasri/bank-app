package com.banking.core.crosscuttingconcerns.exceptions.types;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
} 