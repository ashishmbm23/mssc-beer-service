package com.ashish.msscbeerservice.web.v1.controller;

import com.ashish.msscbeerservice.service.CustomerService;
import com.ashish.msscbeerservice.web.v1.model.CustomerDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(CustomerController.API_V1_CUSTOMER)
@AllArgsConstructor
public class CustomerController {
    public static final String API_V1_CUSTOMER = "/api/v1/customer";
    CustomerService customerService;
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customerDto){
        HttpHeaders headers = getHttpHeaders(customerDto);
        return new ResponseEntity<>(customerService.saveCustomer(customerDto), headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@Valid @PathVariable UUID id, @RequestBody CustomerDto customerDto){
        customerService.updateCustomer(id, customerDto);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    private static HttpHeaders getHttpHeaders(CustomerDto customerDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("location", API_V1_CUSTOMER + "/" + customerDto.getId());
        return headers;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
