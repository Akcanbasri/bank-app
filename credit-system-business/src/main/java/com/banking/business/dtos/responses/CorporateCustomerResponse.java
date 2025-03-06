package com.banking.business.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CorporateCustomerResponse extends CustomerResponse {
    private String companyName;
    private String taxNumber;
    private String tradeRegisterNumber;
    private Double annualTurnover;
    private Integer employeeCount;
} 