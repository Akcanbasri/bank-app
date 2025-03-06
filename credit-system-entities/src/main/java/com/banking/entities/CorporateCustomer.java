package com.banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "corporate_customers")
public class CorporateCustomer extends Customer {

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "tax_number", nullable = false, unique = true)
    private String taxNumber;

    @Column(name = "trade_register_number", nullable = false, unique = true)
    private String tradeRegisterNumber;

    @Column(name = "annual_turnover", nullable = false)
    private Double annualTurnover;

    @Column(name = "employee_count", nullable = false)
    private Integer employeeCount;
}