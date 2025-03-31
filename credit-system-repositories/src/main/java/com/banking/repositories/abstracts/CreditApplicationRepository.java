package com.banking.repositories.abstracts;

import com.banking.entities.CreditApplication;
import com.banking.entities.CreditApplicationStatus;
import com.banking.entities.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditApplicationRepository extends BaseRepository<CreditApplication, Long> {

    List<CreditApplication> findByCustomerId(Long customerId);

    List<CreditApplication> findByCustomerIdAndCustomerType(Long customerId, CustomerType customerType);

    List<CreditApplication> findByCustomerIdAndStatus(Long customerId, CreditApplicationStatus status);

    List<CreditApplication> findByCustomerIdAndCustomerTypeAndStatus(Long customerId, CustomerType customerType,
            CreditApplicationStatus status);

    List<CreditApplication> findByCreditTypeId(Long creditTypeId);

    List<CreditApplication> findByStatus(CreditApplicationStatus status);

    Page<CreditApplication> findByCustomerId(Long customerId, Pageable pageable);

    Page<CreditApplication> findByCustomerIdAndCustomerType(Long customerId, CustomerType customerType,
            Pageable pageable);

    Page<CreditApplication> findByStatus(CreditApplicationStatus status, Pageable pageable);
}