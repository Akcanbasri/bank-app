package com.banking.business.mappings;

import com.banking.business.dtos.requests.CreateCreditTypeRequest;
import com.banking.business.dtos.responses.CreditTypeResponse;
import com.banking.entities.CreditType;
import com.banking.repositories.abstracts.CreditTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreditTypeMapper {

    private final CreditTypeRepository creditTypeRepository;

    public CreditType toEntity(CreateCreditTypeRequest request) {
        CreditType entity = new CreditType();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setCustomerType(request.getCustomerType());
        entity.setInterestRate(request.getInterestRate());
        entity.setMinAmount(request.getMinAmount());
        entity.setMaxAmount(request.getMaxAmount());
        entity.setMinTerm(request.getMinTerm());
        entity.setMaxTerm(request.getMaxTerm());
        entity.setIsActive(request.getIsActive());

        if (request.getParentTypeId() != null) {
            CreditType parentType = creditTypeRepository.findById(request.getParentTypeId())
                    .orElse(null);
            entity.setParentType(parentType);
        }

        return entity;
    }

    public CreditTypeResponse toResponse(CreditType entity) {
        CreditTypeResponse response = new CreditTypeResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setCustomerType(entity.getCustomerType());
        response.setInterestRate(entity.getInterestRate());
        response.setMinAmount(entity.getMinAmount());
        response.setMaxAmount(entity.getMaxAmount());
        response.setMinTerm(entity.getMinTerm());
        response.setMaxTerm(entity.getMaxTerm());
        response.setIsActive(entity.getIsActive());

        if (entity.getParentType() != null) {
            response.setParentTypeId(entity.getParentType().getId());
            response.setParentTypeName(entity.getParentType().getName());
        }

        return response;
    }
}