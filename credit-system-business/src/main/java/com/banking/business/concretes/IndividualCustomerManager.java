package com.banking.business.concretes;

import com.banking.business.abstracts.IndividualCustomerService;
import com.banking.business.constants.Messages;
import com.banking.business.dtos.requests.CreateIndividualCustomerRequest;
import com.banking.business.dtos.responses.IndividualCustomerResponse;
import com.banking.business.mappings.IndividualCustomerMapper;
import com.banking.business.rules.IndividualCustomerBusinessRules;
import com.banking.core.paging.Page;
import com.banking.core.paging.PageRequest;
import com.banking.entities.IndividualCustomer;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IndividualCustomerManager implements IndividualCustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerMapper mapper;
    private final IndividualCustomerBusinessRules rules;

    @Override
    public IndividualCustomerResponse add(CreateIndividualCustomerRequest request) {
        rules.checkIfEmailExists(request.getEmail());
        checkIfNationalIdExists(request.getNationalId());

        IndividualCustomer customer = mapper.toEntity(request);
        customer = individualCustomerRepository.save(customer);
        return mapper.toResponse(customer);
    }

    @Override
    public List<IndividualCustomerResponse> getAll() {
        List<IndividualCustomer> customers = individualCustomerRepository.findAll();
        return customers.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public Page<IndividualCustomerResponse> getAll(PageRequest pageRequest) {
        org.springframework.data.domain.Page<IndividualCustomer> pageData = individualCustomerRepository
                .findAll(pageRequest.getPageable());

        List<IndividualCustomerResponse> responses = pageData.getContent()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return Page.from(new org.springframework.data.domain.PageImpl<>(
                responses,
                pageData.getPageable(),
                pageData.getTotalElements()));
    }

    @Override
    public IndividualCustomerResponse getById(Long id) {
        IndividualCustomer customer = individualCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.Customer.NOT_FOUND));
        return mapper.toResponse(customer);
    }

    @Override
    public void delete(Long id) {
        individualCustomerRepository.deleteById(id);
    }

    @Override
    public IndividualCustomerResponse getByEmail(String email) {
        IndividualCustomer customer = individualCustomerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(Messages.Customer.NOT_FOUND));
        return mapper.toResponse(customer);
    }

    @Override
    public IndividualCustomerResponse getByNationalId(String nationalId) {
        IndividualCustomer customer = individualCustomerRepository.findByNationalId(nationalId)
                .orElseThrow(() -> new RuntimeException(Messages.Customer.NOT_FOUND));
        return mapper.toResponse(customer);
    }

    private void checkIfNationalIdExists(String nationalId) {
        if (individualCustomerRepository.existsByNationalId(nationalId)) {
            throw new RuntimeException(Messages.IndividualCustomer.NATIONAL_ID_ALREADY_EXISTS);
        }
    }
}