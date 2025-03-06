package com.banking.repositories.abstracts;

import com.banking.entities.CorporateCustomer;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CorporateCustomerRepository extends CustomerRepository<CorporateCustomer> {
    
    Optional<CorporateCustomer> findByTaxNumber(String taxNumber);
    
    boolean existsByTaxNumber(String taxNumber);
    
    boolean existsByTaxNumberAndIdNot(String taxNumber, Long id);
    
    Optional<CorporateCustomer> findByTradeRegisterNumber(String tradeRegisterNumber);
    
    boolean existsByTradeRegisterNumber(String tradeRegisterNumber);
    
    boolean existsByTradeRegisterNumberAndIdNot(String tradeRegisterNumber, Long id);
} 