package com.banking.business.mappings;

import com.banking.business.dtos.requests.CreateCreditApplicationRequest;
import com.banking.business.dtos.responses.CreditApplicationResponse;
import com.banking.entities.CreditApplication;
import com.banking.entities.CreditType;
import com.banking.repositories.abstracts.CreditTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreditApplicationMapper {

    private final CreditTypeRepository creditTypeRepository;

    public CreditApplication toEntity(CreateCreditApplicationRequest request) {
        CreditApplication entity = new CreditApplication();

        CreditType creditType = creditTypeRepository.findById(request.getCreditTypeId())
                .orElseThrow(() -> new RuntimeException("Credit type not found"));

        entity.setCreditType(creditType);
        entity.setCustomerId(request.getCustomerId());
        entity.setCustomerType(request.getCustomerType());
        entity.setAmount(request.getAmount());
        entity.setTerm(request.getTerm());
        entity.setNotes(request.getNotes());

        // Set interest rate from credit type
        entity.setInterestRate(creditType.getInterestRate());

        // Calculate monthly payment using simple interest calculation (for
        // demonstration)
        double totalInterest = request.getAmount() * (creditType.getInterestRate() / 100) * (request.getTerm() / 12.0);
        double totalPayment = request.getAmount() + totalInterest;
        double monthlyPayment = totalPayment / request.getTerm();

        entity.setMonthlyPayment(monthlyPayment);
        entity.setTotalPayment(totalPayment);

        return entity;
    }

    public CreditApplicationResponse toResponse(CreditApplication entity) {
        CreditApplicationResponse response = new CreditApplicationResponse();

        response.setId(entity.getId());
        response.setCreditTypeId(entity.getCreditType().getId());
        response.setCreditTypeName(entity.getCreditType().getName());
        response.setInterestRate(entity.getInterestRate());
        response.setCustomerId(entity.getCustomerId());
        response.setCustomerType(entity.getCustomerType());
        response.setAmount(entity.getAmount());
        response.setTerm(entity.getTerm());
        response.setApplicationDate(entity.getApplicationDate());
        response.setStatus(entity.getStatus());
        response.setApprovalDate(entity.getApprovalDate());
        response.setNotes(entity.getNotes());
        response.setMonthlyPayment(entity.getMonthlyPayment());
        response.setTotalPayment(entity.getTotalPayment());

        return response;
    }
}