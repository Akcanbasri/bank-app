package com.banking.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String phoneNumber;
    private String address;
    private String email;
    private boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
} 