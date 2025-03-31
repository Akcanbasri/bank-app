package com.banking.business.dtos.responses;

import com.banking.entities.CreditApplicationStatus;
import com.banking.entities.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationResponse {
    private Long id;
    private Long creditTypeId;
    private String creditTypeName;
    private Double interestRate;
    private Long customerId;
    private CustomerType customerType;
    private Double amount;
    private Integer term;
    private LocalDateTime applicationDate;
    private CreditApplicationStatus status;
    private LocalDateTime approvalDate;
    private String notes;
    private Double monthlyPayment;
    private Double totalPayment;
}