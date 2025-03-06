package com.banking.business.abstracts;

import com.banking.business.dtos.requests.CreateIndividualCustomerRequest;
import com.banking.business.dtos.responses.IndividualCustomerResponse;
import com.banking.entities.IndividualCustomer;

public interface IndividualCustomerService extends CustomerService<IndividualCustomer, IndividualCustomerResponse> {
    IndividualCustomerResponse add(CreateIndividualCustomerRequest request);
    IndividualCustomerResponse getByNationalId(String nationalId);
} 