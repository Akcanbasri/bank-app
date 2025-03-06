package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseCustomerBusinessRules {

    private final CustomerRepository<?> customerRepository;

    protected BaseCustomerBusinessRules(CustomerRepository<?> customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void checkIfEmailExists(String email) {
        if (customerRepository.existsByEmail(email)) {
            throw new BusinessException(Messages.Customer.EMAIL_ALREADY_EXISTS);
        }
    }

    public void checkIfEmailExistsForUpdate(String email, Long id) {
        if (customerRepository.existsByEmailAndIdNot(email, id)) {
            throw new BusinessException(Messages.Customer.EMAIL_ALREADY_EXISTS);
        }
    }
}