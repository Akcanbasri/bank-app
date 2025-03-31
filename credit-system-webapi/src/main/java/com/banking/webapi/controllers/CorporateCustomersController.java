package com.banking.webapi.controllers;

import com.banking.business.abstracts.CorporateCustomerService;
import com.banking.business.dtos.requests.CreateCorporateCustomerRequest;
import com.banking.business.dtos.responses.CorporateCustomerResponse;
import com.banking.core.paging.Page;
import com.banking.core.paging.PageRequest;
import com.banking.core.paging.Sort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corporate-customers")
@AllArgsConstructor
public class CorporateCustomersController {

    private final CorporateCustomerService corporateCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CorporateCustomerResponse add(@RequestBody @Valid CreateCorporateCustomerRequest request) {
        return corporateCustomerService.add(request);
    }

    @GetMapping
    public List<CorporateCustomerResponse> getAll() {
        return corporateCustomerService.getAll();
    }

    @GetMapping("/paged")
    public Page<CorporateCustomerResponse> getAllPaged(
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

        return corporateCustomerService.getAll(pageRequest);
    }

    @GetMapping("/{id}")
    public CorporateCustomerResponse getById(@PathVariable Long id) {
        return corporateCustomerService.getById(id);
    }

    @GetMapping("/by-email/{email}")
    public CorporateCustomerResponse getByEmail(@PathVariable String email) {
        return corporateCustomerService.getByEmail(email);
    }

    @GetMapping("/by-tax-number/{taxNumber}")
    public CorporateCustomerResponse getByTaxNumber(@PathVariable String taxNumber) {
        return corporateCustomerService.getByTaxNumber(taxNumber);
    }

    @GetMapping("/by-trade-register-number/{tradeRegisterNumber}")
    public CorporateCustomerResponse getByTradeRegisterNumber(@PathVariable String tradeRegisterNumber) {
        return corporateCustomerService.getByTradeRegisterNumber(tradeRegisterNumber);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        corporateCustomerService.delete(id);
    }
}