package com.banking.entities;

import com.banking.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "credit_payment_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditPaymentSchedule extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_application_id", nullable = false)
    private CreditApplication creditApplication;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "payment_amount", nullable = false)
    private Double paymentAmount;

    @Column(name = "principal_amount", nullable = false)
    private Double principalAmount;

    @Column(name = "interest_amount", nullable = false)
    private Double interestAmount;

    @Column(name = "remaining_principal", nullable = false)
    private Double remainingPrincipal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = PaymentStatus.PENDING;
        }
    }
}