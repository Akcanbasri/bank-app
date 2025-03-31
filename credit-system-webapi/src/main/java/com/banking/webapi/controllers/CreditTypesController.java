package com.banking.webapi.controllers;

import com.banking.business.abstracts.CreditTypeService;
import com.banking.business.dtos.requests.CreateCreditTypeRequest;
import com.banking.business.dtos.responses.CreditTypeResponse;
import com.banking.core.paging.Page;
import com.banking.core.paging.PageRequest;
import com.banking.core.paging.Sort;
import com.banking.entities.CustomerType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-types")
@AllArgsConstructor
public class CreditTypesController {

    private final CreditTypeService creditTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditTypeResponse add(@RequestBody @Valid CreateCreditTypeRequest request) {
        return creditTypeService.add(request);
    }

    @GetMapping("/{id}")
    public CreditTypeResponse getById(@PathVariable Long id) {
        return creditTypeService.getById(id);
    }

    @GetMapping
    public List<CreditTypeResponse> getAll() {
        return creditTypeService.getAll();
    }

    @GetMapping("/paged")
    public Page<CreditTypeResponse> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortField,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(page);
        pageRequest.setSize(size);

        if (sortField != null && !sortField.isEmpty()) {
            Sort sort = new Sort(sortField, sortDirection);
            pageRequest.setSort(sort);
        }

        return creditTypeService.getAll(pageRequest);
    }

    @GetMapping("/by-customer-type/{customerType}")
    public List<CreditTypeResponse> getAllByCustomerType(@PathVariable CustomerType customerType) {
        return creditTypeService.getAllByCustomerType(customerType);
    }

    @GetMapping("/main-types")
    public List<CreditTypeResponse> getAllMainTypes() {
        return creditTypeService.getAllMainTypes();
    }

    @GetMapping("/main-types/by-customer-type/{customerType}")
    public List<CreditTypeResponse> getAllMainTypesByCustomerType(@PathVariable CustomerType customerType) {
        return creditTypeService.getAllMainTypesByCustomerType(customerType);
    }

    @GetMapping("/sub-types/{parentTypeId}")
    public List<CreditTypeResponse> getAllSubTypes(@PathVariable Long parentTypeId) {
        return creditTypeService.getAllSubTypes(parentTypeId);
    }

    @GetMapping("/sub-types/by-customer-type/{customerType}/{parentTypeId}")
    public List<CreditTypeResponse> getAllSubTypesByCustomerType(
            @PathVariable CustomerType customerType,
            @PathVariable Long parentTypeId) {
        return creditTypeService.getAllSubTypesByCustomerType(customerType, parentTypeId);
    }

    @GetMapping("/active")
    public List<CreditTypeResponse> getAllActive() {
        return creditTypeService.getAllActive();
    }

    @GetMapping("/active/by-customer-type/{customerType}")
    public List<CreditTypeResponse> getAllActiveByCustomerType(@PathVariable CustomerType customerType) {
        return creditTypeService.getAllActiveByCustomerType(customerType);
    }

    @PutMapping("/{id}")
    public CreditTypeResponse update(@PathVariable Long id, @RequestBody @Valid CreateCreditTypeRequest request) {
        return creditTypeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        creditTypeService.delete(id);
    }

    @PatchMapping("/{id}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        creditTypeService.activate(id);
    }

    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        creditTypeService.deactivate(id);
    }
}