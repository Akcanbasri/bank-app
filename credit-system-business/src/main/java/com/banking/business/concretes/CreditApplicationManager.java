package com.banking.business.concretes;

import com.banking.business.abstracts.CreditApplicationService;
import com.banking.business.constants.Messages;
import com.banking.business.dtos.requests.CreateCreditApplicationRequest;
import com.banking.business.dtos.responses.CreditApplicationResponse;
import com.banking.business.mappings.CreditApplicationMapper;
import com.banking.business.rules.CreditApplicationBusinessRules;
import com.banking.core.paging.Page;
import com.banking.core.paging.PageRequest;
import com.banking.entities.CreditApplication;
import com.banking.entities.CreditApplicationStatus;
import com.banking.entities.CustomerType;
import com.banking.repositories.abstracts.CreditApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreditApplicationManager implements CreditApplicationService {

    private final CreditApplicationRepository creditApplicationRepository;
    private final CreditApplicationMapper mapper;
    private final CreditApplicationBusinessRules rules;

    @Override
    public CreditApplicationResponse apply(CreateCreditApplicationRequest request) {
        rules.checkIfCreditTypeExists(request.getCreditTypeId());
        rules.checkIfCustomerExists(request.getCustomerId(), request.getCustomerType());
        rules.checkIfCustomerTypeMatchesCreditType(request.getCreditTypeId(), request.getCustomerType());
        rules.checkIfAmountInRange(request.getCreditTypeId(), request.getAmount());
        rules.checkIfTermInRange(request.getCreditTypeId(), request.getTerm());
        rules.checkIfCreditTypeActive(request.getCreditTypeId());

        CreditApplication application = mapper.toEntity(request);
        application = creditApplicationRepository.save(application);
        return mapper.toResponse(application);
    }

    @Override
    public CreditApplicationResponse getById(Long id) {
        CreditApplication application = findCreditApplicationById(id);
        return mapper.toResponse(application);
    }

    @Override
    public List<CreditApplicationResponse> getAll() {
        return creditApplicationRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CreditApplicationResponse> getAll(PageRequest pageRequest) {
        org.springframework.data.domain.Page<CreditApplication> pageData = creditApplicationRepository
                .findAll(pageRequest.getPageable());

        List<CreditApplicationResponse> responses = pageData.getContent().stream()
                .map(mapper::toResponse)
                .toList();

        return Page.from(new org.springframework.data.domain.PageImpl<>(
                responses,
                pageData.getPageable(),
                pageData.getTotalElements()));
    }

    @Override
    public List<CreditApplicationResponse> getAllByCustomerId(Long customerId) {
        return creditApplicationRepository.findByCustomerId(customerId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditApplicationResponse> getAllByCustomerIdAndCustomerType(Long customerId,
            CustomerType customerType) {
        return creditApplicationRepository.findByCustomerIdAndCustomerType(customerId, customerType).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditApplicationResponse> getAllByCustomerIdAndStatus(Long customerId,
            CreditApplicationStatus status) {
        return creditApplicationRepository.findByCustomerIdAndStatus(customerId, status).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditApplicationResponse> getAllByCustomerIdAndCustomerTypeAndStatus(
            Long customerId, CustomerType customerType, CreditApplicationStatus status) {
        return creditApplicationRepository.findByCustomerIdAndCustomerTypeAndStatus(customerId, customerType, status)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditApplicationResponse> getAllByCreditTypeId(Long creditTypeId) {
        return creditApplicationRepository.findByCreditTypeId(creditTypeId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditApplicationResponse> getAllByStatus(CreditApplicationStatus status) {
        return creditApplicationRepository.findByStatus(status).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CreditApplicationResponse> getAllByCustomerId(Long customerId, PageRequest pageRequest) {
        org.springframework.data.domain.Page<CreditApplication> pageData = creditApplicationRepository
                .findByCustomerId(customerId, pageRequest.getPageable());

        List<CreditApplicationResponse> responses = pageData.getContent().stream()
                .map(mapper::toResponse)
                .toList();

        return Page.from(new org.springframework.data.domain.PageImpl<>(
                responses,
                pageData.getPageable(),
                pageData.getTotalElements()));
    }

    @Override
    public Page<CreditApplicationResponse> getAllByCustomerIdAndCustomerType(
            Long customerId, CustomerType customerType, PageRequest pageRequest) {
        org.springframework.data.domain.Page<CreditApplication> pageData = creditApplicationRepository
                .findByCustomerIdAndCustomerType(customerId, customerType, pageRequest.getPageable());

        List<CreditApplicationResponse> responses = pageData.getContent().stream()
                .map(mapper::toResponse)
                .toList();

        return Page.from(new org.springframework.data.domain.PageImpl<>(
                responses,
                pageData.getPageable(),
                pageData.getTotalElements()));
    }

    @Override
    public Page<CreditApplicationResponse> getAllByStatus(CreditApplicationStatus status, PageRequest pageRequest) {
        org.springframework.data.domain.Page<CreditApplication> pageData = creditApplicationRepository
                .findByStatus(status, pageRequest.getPageable());

        List<CreditApplicationResponse> responses = pageData.getContent().stream()
                .map(mapper::toResponse)
                .toList();

        return Page.from(new org.springframework.data.domain.PageImpl<>(
                responses,
                pageData.getPageable(),
                pageData.getTotalElements()));
    }

    @Override
    public CreditApplicationResponse approve(Long id, String notes) {
        CreditApplication application = findCreditApplicationById(id);

        if (application.getStatus() != CreditApplicationStatus.PENDING) {
            throw new RuntimeException(Messages.CreditApplication.ALREADY_PROCESSED);
        }

        application.setStatus(CreditApplicationStatus.APPROVED);
        application.setApprovalDate(LocalDateTime.now());

        if (notes != null && !notes.isEmpty()) {
            application.setNotes(application.getNotes() + "\n\nApproval Notes: " + notes);
        }

        application = creditApplicationRepository.save(application);
        return mapper.toResponse(application);
    }

    @Override
    public CreditApplicationResponse reject(Long id, String notes) {
        CreditApplication application = findCreditApplicationById(id);

        if (application.getStatus() != CreditApplicationStatus.PENDING) {
            throw new RuntimeException(Messages.CreditApplication.ALREADY_PROCESSED);
        }

        application.setStatus(CreditApplicationStatus.REJECTED);
        application.setApprovalDate(LocalDateTime.now());

        if (notes != null && !notes.isEmpty()) {
            application.setNotes(application.getNotes() + "\n\nRejection Notes: " + notes);
        }

        application = creditApplicationRepository.save(application);
        return mapper.toResponse(application);
    }

    private CreditApplication findCreditApplicationById(Long id) {
        return creditApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.CreditApplication.NOT_FOUND));
    }
}