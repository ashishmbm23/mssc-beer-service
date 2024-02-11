package com.ashish.msscbeerservice.service;

import com.ashish.msscbeerservice.mapper.CustomerMapper;
import com.ashish.msscbeerservice.repository.CustomerRepository;
import com.ashish.msscbeerservice.util.Util;
import com.ashish.msscbeerservice.web.v1.model.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final Util util;
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    CustomerDto customerDto = customerMapper.convertCustomerToCustomerDto(customer);
                    util.setUrl(customerDto);
                    return customerDto;
                }).orElseThrow(() -> new RuntimeException("customer id not found."));
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        log.info("save customer");
        return CustomerDto.builder().
                id(UUID.randomUUID()).
                build();
    }

    @Override
    public CustomerDto updateCustomer(UUID id, CustomerDto customerDto) {
        log.info("update customer:" + id);
        return null;
    }

    @Override
    public void deleteCustomer(UUID id) {
        log.info("delete customer");
    }


}
