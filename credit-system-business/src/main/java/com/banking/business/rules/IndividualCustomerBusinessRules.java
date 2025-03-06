package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.CustomerRepository;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class IndividualCustomerBusinessRules extends BaseCustomerBusinessRules {

    private final IndividualCustomerRepository individualCustomerRepository;

    public IndividualCustomerBusinessRules(CustomerRepository<?> customerRepository,
            IndividualCustomerRepository individualCustomerRepository) {
        super(customerRepository);
        this.individualCustomerRepository = individualCustomerRepository;
    }

    public void checkIfNationalIdExists(String nationalId) {
        if (individualCustomerRepository.existsByNationalId(nationalId)) {
            throw new BusinessException(Messages.IndividualCustomer.NATIONAL_ID_ALREADY_EXISTS);
        }
    }

    public void checkIfNationalIdExistsForUpdate(String nationalId, Long id) {
        if (individualCustomerRepository.existsByNationalIdAndIdNot(nationalId, id)) {
            throw new BusinessException(Messages.IndividualCustomer.NATIONAL_ID_ALREADY_EXISTS);
        }
    }
}