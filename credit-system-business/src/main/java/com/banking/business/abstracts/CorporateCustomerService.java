package com.banking.business.abstracts;

import com.banking.business.dtos.requests.CreateCorporateCustomerRequest;
import com.banking.business.dtos.responses.CorporateCustomerResponse;
import com.banking.entities.CorporateCustomer;

public interface CorporateCustomerService extends CustomerService<CorporateCustomer, CorporateCustomerResponse> {
    CorporateCustomerResponse add(CreateCorporateCustomerRequest request);
    CorporateCustomerResponse getByTaxNumber(String taxNumber);
    CorporateCustomerResponse getByTradeRegisterNumber(String tradeRegisterNumber);
} 