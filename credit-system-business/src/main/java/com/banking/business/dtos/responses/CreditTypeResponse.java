package com.banking.business.dtos.responses;

import com.banking.entities.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditTypeResponse {
    private Long id;
    private String name;
    private String description;
    private CustomerType customerType;
    private Long parentTypeId;
    private String parentTypeName;
    private Double interestRate;
    private Double minAmount;
    private Double maxAmount;
    private Integer minTerm;
    private Integer maxTerm;
    private Boolean isActive;
}