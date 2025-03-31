package com.banking.entities;

import com.banking.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "credit_application_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationDocument extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_application_id", nullable = false)
    private CreditApplication creditApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requirement_id", nullable = false)
    private CreditRequirement requirement;

    @Column(name = "document_url", nullable = false)
    private String documentUrl;

    @Column(name = "upload_date", nullable = false)
    private LocalDateTime uploadDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DocumentStatus status;

    @PrePersist
    public void prePersist() {
        this.uploadDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = DocumentStatus.PENDING;
        }
    }
}