package com.banking.business.dtos.requests;

import com.banking.entities.CustomerType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditApplicationRequest {

    @NotNull(message = "Credit type id is required")
    private Long creditTypeId;

    @NotNull(message = "Customer id is required")
    private Long customerId;

    @NotNull(message = "Customer type is required")
    private CustomerType customerType;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive")
    private Double amount;

    @NotNull(message = "Term is required")
    @Min(value = 1, message = "Term must be at least 1 month")
    private Integer term;

    @Size(max = 1000, message = "Notes cannot exceed 1000 characters")
    private String notes;
}