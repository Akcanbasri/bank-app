package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.entities.CreditType;
import com.banking.entities.CustomerType;
import com.banking.repositories.abstracts.CreditTypeRepository;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditApplicationBusinessRules {

    private final CreditTypeRepository creditTypeRepository;
    private final IndividualCustomerRepository individualCustomerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;

    public void checkIfCreditTypeExists(Long creditTypeId) {
        if (!creditTypeRepository.existsById(creditTypeId)) {
            throw new RuntimeException(Messages.CreditType.NOT_FOUND);
        }
    }

    public void checkIfCustomerExists(Long customerId, CustomerType customerType) {
        if (customerType == CustomerType.INDIVIDUAL) {
            if (!individualCustomerRepository.existsById(customerId)) {
                throw new RuntimeException(Messages.Customer.NOT_FOUND);
            }
        } else if (customerType == CustomerType.CORPORATE) {
            if (!corporateCustomerRepository.existsById(customerId)) {
                throw new RuntimeException(Messages.Customer.NOT_FOUND);
            }
        } else {
            throw new RuntimeException("Invalid customer type");
        }
    }

    public void checkIfCustomerTypeMatchesCreditType(Long creditTypeId, CustomerType customerType) {
        CreditType creditType = creditTypeRepository.findById(creditTypeId)
                .orElseThrow(() -> new RuntimeException(Messages.CreditType.NOT_FOUND));

        if (creditType.getCustomerType() != customerType) {
            throw new RuntimeException(Messages.CreditApplication.CUSTOMER_TYPE_MISMATCH);
        }
    }

    public void checkIfAmountInRange(Long creditTypeId, Double amount) {
        CreditType creditType = creditTypeRepository.findById(creditTypeId)
                .orElseThrow(() -> new RuntimeException(Messages.CreditType.NOT_FOUND));

        if (amount < creditType.getMinAmount() || amount > creditType.getMaxAmount()) {
            throw new RuntimeException(Messages.CreditApplication.AMOUNT_OUT_OF_RANGE);
        }
    }

    public void checkIfTermInRange(Long creditTypeId, Integer term) {
        CreditType creditType = creditTypeRepository.findById(creditTypeId)
                .orElseThrow(() -> new RuntimeException(Messages.CreditType.NOT_FOUND));

        if (term < creditType.getMinTerm() || term > creditType.getMaxTerm()) {
            throw new RuntimeException(Messages.CreditApplication.TERM_OUT_OF_RANGE);
        }
    }

    public void checkIfCreditTypeActive(Long creditTypeId) {
        CreditType creditType = creditTypeRepository.findById(creditTypeId)
                .orElseThrow(() -> new RuntimeException(Messages.CreditType.NOT_FOUND));

        if (!creditType.getIsActive()) {
            throw new RuntimeException(Messages.CreditApplication.CREDIT_TYPE_NOT_ACTIVE);
        }
    }
}