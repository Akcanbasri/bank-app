package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.entities.CustomerType;
import com.banking.repositories.abstracts.CreditTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditTypeBusinessRules {

    private final CreditTypeRepository creditTypeRepository;

    public void checkIfNameExistsForCustomerType(String name, CustomerType customerType) {
        if (creditTypeRepository.findByNameAndCustomerType(name, customerType).isPresent()) {
            throw new RuntimeException(Messages.CreditType.NAME_ALREADY_EXISTS);
        }
    }

    public void checkIfParentTypeExists(Long parentTypeId) {
        if (parentTypeId != null && !creditTypeRepository.existsById(parentTypeId)) {
            throw new RuntimeException(Messages.CreditType.PARENT_TYPE_NOT_FOUND);
        }
    }

    public void checkIfAmountRangeValid(Double minAmount, Double maxAmount) {
        if (minAmount >= maxAmount) {
            throw new RuntimeException(Messages.CreditType.INVALID_AMOUNT_RANGE);
        }
    }

    public void checkIfTermRangeValid(Integer minTerm, Integer maxTerm) {
        if (minTerm >= maxTerm) {
            throw new RuntimeException(Messages.CreditType.INVALID_TERM_RANGE);
        }
    }
}