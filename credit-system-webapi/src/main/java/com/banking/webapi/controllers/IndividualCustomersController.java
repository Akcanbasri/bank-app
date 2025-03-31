package com.banking.webapi.controllers;

import com.banking.business.abstracts.IndividualCustomerService;
import com.banking.business.dtos.requests.CreateIndividualCustomerRequest;
import com.banking.business.dtos.responses.IndividualCustomerResponse;
import com.banking.core.paging.Page;
import com.banking.core.paging.PageRequest;
import com.banking.core.paging.Sort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individual-customers")
@AllArgsConstructor
public class IndividualCustomersController {

    private final IndividualCustomerService individualCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndividualCustomerResponse add(@RequestBody @Valid CreateIndividualCustomerRequest request) {
        return individualCustomerService.add(request);
    }

    @GetMapping
    public List<IndividualCustomerResponse> getAll() {
        return individualCustomerService.getAll();
    }

    @GetMapping("/paged")
    public Page<IndividualCustomerResponse> getAllPaged(
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

        return individualCustomerService.getAll(pageRequest);
    }

    @GetMapping("/{id}")
    public IndividualCustomerResponse getById(@PathVariable Long id) {
        return individualCustomerService.getById(id);
    }

    @GetMapping("/by-email/{email}")
    public IndividualCustomerResponse getByEmail(@PathVariable String email) {
        return individualCustomerService.getByEmail(email);
    }

    @GetMapping("/by-national-id/{nationalId}")
    public IndividualCustomerResponse getByNationalId(@PathVariable String nationalId) {
        return individualCustomerService.getByNationalId(nationalId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        individualCustomerService.delete(id);
    }
}