package com.banking.core.crosscuttingconcerns.exceptions.problemdetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetails {
    private String title;
    private String type;
    private String detail;
    private int status;
    private String instance;
    private Map<String, Object> extensions;
} 