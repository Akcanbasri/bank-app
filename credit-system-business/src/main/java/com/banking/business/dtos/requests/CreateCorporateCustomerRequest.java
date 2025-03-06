package com.banking.business.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomerRequest {
    
    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;
    
    @NotBlank(message = "Tax number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Tax number must be 10 digits")
    private String taxNumber;
    
    @NotBlank(message = "Trade register number is required")
    private String tradeRegisterNumber;
    
    @NotNull(message = "Annual turnover is required")
    @Positive(message = "Annual turnover must be positive")
    private Double annualTurnover;
    
    @NotNull(message = "Employee count is required")
    @Positive(message = "Employee count must be positive")
    private Integer employeeCount;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;
    
    @NotBlank(message = "Address is required")
    @Size(min = 10, max = 200, message = "Address must be between 10 and 200 characters")
    private String address;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
} 