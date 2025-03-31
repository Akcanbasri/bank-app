package com.banking.webapi.controllers;

import com.banking.business.abstracts.CreditApplicationService;
import com.banking.business.dtos.requests.CreateCreditApplicationRequest;
import com.banking.business.dtos.responses.CreditApplicationResponse;
import com.banking.core.paging.Page;
import com.banking.core.paging.PageRequest;
import com.banking.core.paging.Sort;
import com.banking.entities.CreditApplicationStatus;
import com.banking.entities.CustomerType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-applications")
@AllArgsConstructor
public class CreditApplicationsController {

    private final CreditApplicationService creditApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditApplicationResponse apply(@RequestBody @Valid CreateCreditApplicationRequest request) {
        return creditApplicationService.apply(request);
    }

    @GetMapping("/{id}")
    public CreditApplicationResponse getById(@PathVariable Long id) {
        return creditApplicationService.getById(id);
    }

    @GetMapping
    public List<CreditApplicationResponse> getAll() {
        return creditApplicationService.getAll();
    }

    @GetMapping("/paged")
    public Page<CreditApplicationResponse> getAllPaged(
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

        return creditApplicationService.getAll(pageRequest);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<CreditApplicationResponse> getAllByCustomerId(@PathVariable Long customerId) {
        return creditApplicationService.getAllByCustomerId(customerId);
    }

    @GetMapping("/by-customer/{customerId}/paged")
    public Page<CreditApplicationResponse> getAllByCustomerIdPaged(
            @PathVariable Long customerId,
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

        return creditApplicationService.getAllByCustomerId(customerId, pageRequest);
    }

    @GetMapping("/by-customer/{customerId}/customer-type/{customerType}")
    public List<CreditApplicationResponse> getAllByCustomerIdAndCustomerType(
            @PathVariable Long customerId,
            @PathVariable CustomerType customerType) {
        return creditApplicationService.getAllByCustomerIdAndCustomerType(customerId, customerType);
    }

    @GetMapping("/by-customer/{customerId}/status/{status}")
    public List<CreditApplicationResponse> getAllByCustomerIdAndStatus(
            @PathVariable Long customerId,
            @PathVariable CreditApplicationStatus status) {
        return creditApplicationService.getAllByCustomerIdAndStatus(customerId, status);
    }

    @GetMapping("/by-credit-type/{creditTypeId}")
    public List<CreditApplicationResponse> getAllByCreditTypeId(@PathVariable Long creditTypeId) {
        return creditApplicationService.getAllByCreditTypeId(creditTypeId);
    }

    @GetMapping("/by-status/{status}")
    public List<CreditApplicationResponse> getAllByStatus(@PathVariable CreditApplicationStatus status) {
        return creditApplicationService.getAllByStatus(status);
    }

    @GetMapping("/by-status/{status}/paged")
    public Page<CreditApplicationResponse> getAllByStatusPaged(
            @PathVariable CreditApplicationStatus status,
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

        return creditApplicationService.getAllByStatus(status, pageRequest);
    }

    @PatchMapping("/{id}/approve")
    public CreditApplicationResponse approve(
            @PathVariable Long id,
            @RequestParam(required = false) String notes) {
        return creditApplicationService.approve(id, notes);
    }

    @PatchMapping("/{id}/reject")
    public CreditApplicationResponse reject(
            @PathVariable Long id,
            @RequestParam(required = false) String notes) {
        return creditApplicationService.reject(id, notes);
    }
}