package com.banking.repositories.abstracts;

import com.banking.entities.CreditType;
import com.banking.entities.CustomerType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditTypeRepository extends BaseRepository<CreditType, Long> {

    List<CreditType> findByCustomerType(CustomerType customerType);

    List<CreditType> findByParentTypeIsNull();

    List<CreditType> findByParentTypeId(Long parentTypeId);

    List<CreditType> findByCustomerTypeAndParentTypeIsNull(CustomerType customerType);

    List<CreditType> findByCustomerTypeAndParentTypeId(CustomerType customerType, Long parentTypeId);

    List<CreditType> findByIsActiveTrue();

    List<CreditType> findByCustomerTypeAndIsActiveTrue(CustomerType customerType);

    Optional<CreditType> findByNameAndCustomerType(String name, CustomerType customerType);
}