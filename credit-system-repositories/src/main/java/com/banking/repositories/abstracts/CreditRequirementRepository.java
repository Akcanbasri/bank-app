package com.banking.repositories.abstracts;

import com.banking.entities.CreditRequirement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRequirementRepository extends BaseRepository<CreditRequirement, Long> {

    List<CreditRequirement> findByCreditTypeId(Long creditTypeId);

    List<CreditRequirement> findByCreditTypeIdAndIsRequiredTrue(Long creditTypeId);
}