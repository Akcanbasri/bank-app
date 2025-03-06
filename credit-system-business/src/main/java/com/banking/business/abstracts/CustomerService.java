package com.banking.business.abstracts;

import com.banking.business.dtos.responses.CustomerResponse;
import com.banking.entities.Customer;

import java.util.List;

public interface CustomerService<T extends Customer, R extends CustomerResponse> {
    List<R> getAll();
    R getById(Long id);
    void delete(Long id);
    R getByEmail(String email);
} 