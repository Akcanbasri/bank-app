package com.banking.entities;

import com.banking.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "credit_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplication extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_type_id", nullable = false)
    private CreditType creditType;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type", nullable = false)
    private CustomerType customerType;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "term", nullable = false)
    private Integer term;

    @Column(name = "application_date", nullable = false)
    private LocalDateTime applicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CreditApplicationStatus status;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "monthly_payment")
    private Double monthlyPayment;

    @Column(name = "total_payment")
    private Double totalPayment;

    @PrePersist
    public void prePersist() {
        this.applicationDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = CreditApplicationStatus.PENDING;
        }
    }
}