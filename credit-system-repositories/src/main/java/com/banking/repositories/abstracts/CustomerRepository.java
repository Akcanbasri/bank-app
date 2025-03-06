package com.banking.repositories.abstracts;

import com.banking.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CustomerRepository<T extends Customer> extends BaseRepository<T, Long> {
    
    Optional<T> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM #{#entityName} c WHERE c.email = ?1 AND c.id <> ?2")
    boolean existsByEmailAndIdNot(String email, Long id);
} 