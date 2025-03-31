package com.banking.business.abstracts;

import com.banking.business.dtos.requests.CreateCreditTypeRequest;
import com.banking.business.dtos.responses.CreditTypeResponse;
import com.banking.core.paging.Page;
import com.banking.core.paging.PageRequest;
import com.banking.entities.CustomerType;

import java.util.List;

public interface CreditTypeService {
    CreditTypeResponse add(CreateCreditTypeRequest request);

    CreditTypeResponse getById(Long id);

    List<CreditTypeResponse> getAll();

    Page<CreditTypeResponse> getAll(PageRequest pageRequest);

    List<CreditTypeResponse> getAllByCustomerType(CustomerType customerType);

    List<CreditTypeResponse> getAllMainTypes();

    List<CreditTypeResponse> getAllMainTypesByCustomerType(CustomerType customerType);

    List<CreditTypeResponse> getAllSubTypes(Long parentTypeId);

    List<CreditTypeResponse> getAllSubTypesByCustomerType(CustomerType customerType, Long parentTypeId);

    List<CreditTypeResponse> getAllActive();

    List<CreditTypeResponse> getAllActiveByCustomerType(CustomerType customerType);

    CreditTypeResponse update(Long id, CreateCreditTypeRequest request);

    void delete(Long id);

    void activate(Long id);

    void deactivate(Long id);
}