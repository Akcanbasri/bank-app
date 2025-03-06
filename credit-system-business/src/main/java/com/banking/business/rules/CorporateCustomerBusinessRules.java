package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.CustomerRepository;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class CorporateCustomerBusinessRules extends BaseCustomerBusinessRules {

    private final CorporateCustomerRepository corporateCustomerRepository;

    public CorporateCustomerBusinessRules(CustomerRepository<?> customerRepository,
            CorporateCustomerRepository corporateCustomerRepository) {
        super(customerRepository);
        this.corporateCustomerRepository = corporateCustomerRepository;
    }

    public void checkIfTaxNumberExists(String taxNumber) {
        if (corporateCustomerRepository.existsByTaxNumber(taxNumber)) {
            throw new BusinessException(Messages.CorporateCustomer.TAX_NUMBER_ALREADY_EXISTS);
        }
    }

    public void checkIfTaxNumberExistsForUpdate(String taxNumber, Long id) {
        if (corporateCustomerRepository.existsByTaxNumberAndIdNot(taxNumber, id)) {
            throw new BusinessException(Messages.CorporateCustomer.TAX_NUMBER_ALREADY_EXISTS);
        }
    }

    public void checkIfTradeRegisterNumberExists(String tradeRegisterNumber) {
        if (corporateCustomerRepository.existsByTradeRegisterNumber(tradeRegisterNumber)) {
            throw new BusinessException(Messages.CorporateCustomer.TRADE_REGISTER_NUMBER_ALREADY_EXISTS);
        }
    }

    public void checkIfTradeRegisterNumberExistsForUpdate(String tradeRegisterNumber, Long id) {
        if (corporateCustomerRepository.existsByTradeRegisterNumberAndIdNot(tradeRegisterNumber, id)) {
            throw new BusinessException(Messages.CorporateCustomer.TRADE_REGISTER_NUMBER_ALREADY_EXISTS);
        }
    }
}