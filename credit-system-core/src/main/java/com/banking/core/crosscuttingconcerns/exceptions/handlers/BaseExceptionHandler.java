package com.banking.core.crosscuttingconcerns.exceptions.handlers;

import com.banking.core.crosscuttingconcerns.exceptions.problemdetails.*;
import com.banking.core.crosscuttingconcerns.exceptions.types.AuthorizationException;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.core.crosscuttingconcerns.exceptions.types.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public abstract class BaseExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException exception, HttpServletRequest request) {
        BusinessProblemDetails details = new BusinessProblemDetails(exception.getMessage());
        details.setInstance(request.getRequestURI());
        return details;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception, 
                                                            HttpServletRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
            validationErrors.put(error.getField(), error.getDefaultMessage())
        );

        ValidationProblemDetails details = new ValidationProblemDetails(validationErrors);
        details.setInstance(request.getRequestURI());
        return details;
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthorizationProblemDetails handleAuthorizationException(AuthorizationException exception,
                                                                  HttpServletRequest request) {
        AuthorizationProblemDetails details = new AuthorizationProblemDetails();
        details.setInstance(request.getRequestURI());
        return details;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundProblemDetails handleNotFoundException(NotFoundException exception,
                                                        HttpServletRequest request) {
        NotFoundProblemDetails details = new NotFoundProblemDetails(exception.getMessage());
        details.setInstance(request.getRequestURI());
        return details;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public InternalServerErrorProblemDetails handleException(Exception exception,
                                                           HttpServletRequest request) {
        InternalServerErrorProblemDetails details = new InternalServerErrorProblemDetails();
        details.setInstance(request.getRequestURI());
        return details;
    }
} 