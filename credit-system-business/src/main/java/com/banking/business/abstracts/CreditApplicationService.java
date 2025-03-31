package com.banking.business.abstracts;

import com.banking.business.dtos.requests.CreateCreditApplicationRequest;
import com.banking.business.dtos.responses.CreditApplicationResponse;
import com.banking.core.paging.Page;
import com.banking.core.paging.PageRequest;
import com.banking.entities.CreditApplicationStatus;
import com.banking.entities.CustomerType;

import java.util.List;

public interface CreditApplicationService {
    CreditApplicationResponse apply(CreateCreditApplicationRequest request);

    CreditApplicationResponse getById(Long id);

    List<CreditApplicationResponse> getAll();

    Page<CreditApplicationResponse> getAll(PageRequest pageRequest);

    List<CreditApplicationResponse> getAllByCustomerId(Long customerId);

    List<CreditApplicationResponse> getAllByCustomerIdAndCustomerType(Long customerId, CustomerType customerType);

    List<CreditApplicationResponse> getAllByCustomerIdAndStatus(Long customerId, CreditApplicationStatus status);

    List<CreditApplicationResponse> getAllByCustomerIdAndCustomerTypeAndStatus(
            Long customerId, CustomerType customerType, CreditApplicationStatus status);

    List<CreditApplicationResponse> getAllByCreditTypeId(Long creditTypeId);

    List<CreditApplicationResponse> getAllByStatus(CreditApplicationStatus status);

    Page<CreditApplicationResponse> getAllByCustomerId(Long customerId, PageRequest pageRequest);

    Page<CreditApplicationResponse> getAllByCustomerIdAndCustomerType(
            Long customerId, CustomerType customerType, PageRequest pageRequest);

    Page<CreditApplicationResponse> getAllByStatus(CreditApplicationStatus status, PageRequest pageRequest);

    CreditApplicationResponse approve(Long id, String notes);

    CreditApplicationResponse reject(Long id, String notes);
}