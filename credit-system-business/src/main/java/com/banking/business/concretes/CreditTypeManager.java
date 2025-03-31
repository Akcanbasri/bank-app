package com.banking.business.concretes;

import com.banking.business.abstracts.CreditTypeService;
import com.banking.business.constants.Messages;
import com.banking.business.dtos.requests.CreateCreditTypeRequest;
import com.banking.business.dtos.responses.CreditTypeResponse;
import com.banking.business.mappings.CreditTypeMapper;
import com.banking.business.rules.CreditTypeBusinessRules;
import com.banking.core.paging.Page;
import com.banking.core.paging.PageRequest;
import com.banking.entities.CreditType;
import com.banking.entities.CustomerType;
import com.banking.repositories.abstracts.CreditTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreditTypeManager implements CreditTypeService {

    private final CreditTypeRepository creditTypeRepository;
    private final CreditTypeMapper mapper;
    private final CreditTypeBusinessRules rules;

    @Override
    public CreditTypeResponse add(CreateCreditTypeRequest request) {
        rules.checkIfNameExistsForCustomerType(request.getName(), request.getCustomerType());
        rules.checkIfParentTypeExists(request.getParentTypeId());
        rules.checkIfAmountRangeValid(request.getMinAmount(), request.getMaxAmount());
        rules.checkIfTermRangeValid(request.getMinTerm(), request.getMaxTerm());

        CreditType creditType = mapper.toEntity(request);
        creditTypeRepository.save(creditType);
        return mapper.toResponse(creditType);
    }

    @Override
    public CreditTypeResponse getById(Long id) {
        CreditType creditType = findCreditTypeById(id);
        return mapper.toResponse(creditType);
    }

    @Override
    public List<CreditTypeResponse> getAll() {
        return creditTypeRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CreditTypeResponse> getAll(PageRequest pageRequest) {
        org.springframework.data.domain.Page<CreditType> pageData = creditTypeRepository
                .findAll(pageRequest.getPageable());

        List<CreditTypeResponse> responses = pageData.getContent().stream()
                .map(mapper::toResponse)
                .toList();

        return Page.from(new org.springframework.data.domain.PageImpl<>(
                responses,
                pageData.getPageable(),
                pageData.getTotalElements()));
    }

    @Override
    public List<CreditTypeResponse> getAllByCustomerType(CustomerType customerType) {
        return creditTypeRepository.findByCustomerType(customerType).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditTypeResponse> getAllMainTypes() {
        return creditTypeRepository.findByParentTypeIsNull().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditTypeResponse> getAllMainTypesByCustomerType(CustomerType customerType) {
        return creditTypeRepository.findByCustomerTypeAndParentTypeIsNull(customerType).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditTypeResponse> getAllSubTypes(Long parentTypeId) {
        return creditTypeRepository.findByParentTypeId(parentTypeId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditTypeResponse> getAllSubTypesByCustomerType(CustomerType customerType, Long parentTypeId) {
        return creditTypeRepository.findByCustomerTypeAndParentTypeId(customerType, parentTypeId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditTypeResponse> getAllActive() {
        return creditTypeRepository.findByIsActiveTrue().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditTypeResponse> getAllActiveByCustomerType(CustomerType customerType) {
        return creditTypeRepository.findByCustomerTypeAndIsActiveTrue(customerType).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CreditTypeResponse update(Long id, CreateCreditTypeRequest request) {
        CreditType existingCreditType = findCreditTypeById(id);

        // If name changed, check if the new name already exists
        if (!existingCreditType.getName().equals(request.getName())) {
            rules.checkIfNameExistsForCustomerType(request.getName(), request.getCustomerType());
        }

        rules.checkIfParentTypeExists(request.getParentTypeId());
        rules.checkIfAmountRangeValid(request.getMinAmount(), request.getMaxAmount());
        rules.checkIfTermRangeValid(request.getMinTerm(), request.getMaxTerm());

        // Update the existing credit type
        existingCreditType.setName(request.getName());
        existingCreditType.setDescription(request.getDescription());
        existingCreditType.setCustomerType(request.getCustomerType());
        existingCreditType.setInterestRate(request.getInterestRate());
        existingCreditType.setMinAmount(request.getMinAmount());
        existingCreditType.setMaxAmount(request.getMaxAmount());
        existingCreditType.setMinTerm(request.getMinTerm());
        existingCreditType.setMaxTerm(request.getMaxTerm());
        existingCreditType.setIsActive(request.getIsActive());

        if (request.getParentTypeId() != null) {
            CreditType parentType = creditTypeRepository.findById(request.getParentTypeId())
                    .orElse(null);
            existingCreditType.setParentType(parentType);
        } else {
            existingCreditType.setParentType(null);
        }

        creditTypeRepository.save(existingCreditType);
        return mapper.toResponse(existingCreditType);
    }

    @Override
    public void delete(Long id) {
        findCreditTypeById(id); // Check if exists
        creditTypeRepository.deleteById(id);
    }

    @Override
    public void activate(Long id) {
        CreditType creditType = findCreditTypeById(id);
        creditType.setIsActive(true);
        creditTypeRepository.save(creditType);
    }

    @Override
    public void deactivate(Long id) {
        CreditType creditType = findCreditTypeById(id);
        creditType.setIsActive(false);
        creditTypeRepository.save(creditType);
    }

    private CreditType findCreditTypeById(Long id) {
        return creditTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.CreditType.NOT_FOUND));
    }
}