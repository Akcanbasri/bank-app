package com.banking.business.dtos.requests;

import com.banking.entities.CustomerType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditTypeRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Customer type is required")
    private CustomerType customerType;

    private Long parentTypeId;

    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Interest rate must be positive")
    @DecimalMax(value = "100.0", inclusive = true, message = "Interest rate cannot exceed 100%")
    private Double interestRate;

    @NotNull(message = "Minimum amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Minimum amount must be positive")
    private Double minAmount;

    @NotNull(message = "Maximum amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Maximum amount must be positive")
    private Double maxAmount;

    @NotNull(message = "Minimum term is required")
    @Min(value = 1, message = "Minimum term must be at least 1 month")
    private Integer minTerm;

    @NotNull(message = "Maximum term is required")
    @Min(value = 1, message = "Maximum term must be at least 1 month")
    private Integer maxTerm;

    private Boolean isActive = true;
}