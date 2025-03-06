package com.banking.webapi.controllers;

import com.banking.business.abstracts.IndividualCustomerService;
import com.banking.business.dtos.requests.CreateIndividualCustomerRequest;
import com.banking.business.dtos.responses.IndividualCustomerResponse;
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