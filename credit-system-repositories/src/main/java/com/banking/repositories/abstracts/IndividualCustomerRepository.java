package com.banking.repositories.abstracts;

import com.banking.entities.IndividualCustomer;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IndividualCustomerRepository extends CustomerRepository<IndividualCustomer> {
    
    Optional<IndividualCustomer> findByNationalId(String nationalId);
    
    boolean existsByNationalId(String nationalId);
    
    boolean existsByNationalIdAndIdNot(String nationalId, Long id);
} 