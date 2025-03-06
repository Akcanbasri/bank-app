package com.banking.business.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class IndividualCustomerResponse extends CustomerResponse {
    private String firstName;
    private String lastName;
    private String nationalId;
    private LocalDate birthDate;
    private Double monthlyIncome;
} 