package com.banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "individual_customers")
public class IndividualCustomer extends Customer {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "national_id", nullable = false, unique = true, length = 11)
    private String nationalId;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "monthly_income", nullable = false)
    private Double monthlyIncome;
}