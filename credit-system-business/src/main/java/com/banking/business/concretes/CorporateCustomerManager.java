package com.banking.business.concretes;

import com.banking.business.abstracts.CorporateCustomerService;
import com.banking.business.constants.Messages;
import com.banking.business.dtos.requests.CreateCorporateCustomerRequest;
import com.banking.business.dtos.responses.CorporateCustomerResponse;
import com.banking.business.mappings.CorporateCustomerMapper;
import com.banking.business.rules.CorporateCustomerBusinessRules;
import com.banking.core.paging.Page;
import com.banking.core.paging.PageRequest;
import com.banking.entities.CorporateCustomer;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {

    private final CorporateCustomerRepository corporateCustomerRepository;
    private final CorporateCustomerMapper mapper;
    private final CorporateCustomerBusinessRules rules;

    @Override
    public CorporateCustomerResponse add(CreateCorporateCustomerRequest request) {
        rules.checkIfEmailExists(request.getEmail());
        checkIfTaxNumberExists(request.getTaxNumber());
        checkIfTradeRegisterNumberExists(request.getTradeRegisterNumber());

        CorporateCustomer customer = mapper.toEntity(request);
        customer = corporateCustomerRepository.save(customer);
        return mapper.toResponse(customer);
    }

    @Override
    public List<CorporateCustomerResponse> getAll() {
        List<CorporateCustomer> customers = corporateCustomerRepository.findAll();
        return customers.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public Page<CorporateCustomerResponse> getAll(PageRequest pageRequest) {
        org.springframework.data.domain.Page<CorporateCustomer> pageData = corporateCustomerRepository
                .findAll(pageRequest.getPageable());

        List<CorporateCustomerResponse> responses = pageData.getContent()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return Page.from(new org.springframework.data.domain.PageImpl<>(
                responses,
                pageData.getPageable(),
                pageData.getTotalElements()));
    }

    @Override
    public CorporateCustomerResponse getById(Long id) {
        CorporateCustomer customer = corporateCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.Customer.NOT_FOUND));
        return mapper.toResponse(customer);
    }

    @Override
    public void delete(Long id) {
        corporateCustomerRepository.deleteById(id);
    }

    @Override
    public CorporateCustomerResponse getByEmail(String email) {
        CorporateCustomer customer = corporateCustomerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(Messages.Customer.NOT_FOUND));
        return mapper.toResponse(customer);
    }

    @Override
    public CorporateCustomerResponse getByTaxNumber(String taxNumber) {
        CorporateCustomer customer = corporateCustomerRepository.findByTaxNumber(taxNumber)
                .orElseThrow(() -> new RuntimeException(Messages.Customer.NOT_FOUND));
        return mapper.toResponse(customer);
    }

    @Override
    public CorporateCustomerResponse getByTradeRegisterNumber(String tradeRegisterNumber) {
        CorporateCustomer customer = corporateCustomerRepository.findByTradeRegisterNumber(tradeRegisterNumber)
                .orElseThrow(() -> new RuntimeException(Messages.Customer.NOT_FOUND));
        return mapper.toResponse(customer);
    }

    private void checkIfTaxNumberExists(String taxNumber) {
        if (corporateCustomerRepository.existsByTaxNumber(taxNumber)) {
            throw new RuntimeException(Messages.CorporateCustomer.TAX_NUMBER_ALREADY_EXISTS);
        }
    }

    private void checkIfTradeRegisterNumberExists(String tradeRegisterNumber) {
        if (corporateCustomerRepository.existsByTradeRegisterNumber(tradeRegisterNumber)) {
            throw new RuntimeException(Messages.CorporateCustomer.TRADE_REGISTER_NUMBER_ALREADY_EXISTS);
        }
    }
}