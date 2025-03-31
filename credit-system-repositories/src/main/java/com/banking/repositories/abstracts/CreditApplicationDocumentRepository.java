package com.banking.repositories.abstracts;

import com.banking.entities.CreditApplicationDocument;
import com.banking.entities.DocumentStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditApplicationDocumentRepository extends BaseRepository<CreditApplicationDocument, Long> {

    List<CreditApplicationDocument> findByCreditApplicationId(Long creditApplicationId);

    List<CreditApplicationDocument> findByCreditApplicationIdAndStatus(Long creditApplicationId, DocumentStatus status);

    List<CreditApplicationDocument> findByRequirementId(Long requirementId);

    Optional<CreditApplicationDocument> findByCreditApplicationIdAndRequirementId(Long creditApplicationId,
            Long requirementId);
}