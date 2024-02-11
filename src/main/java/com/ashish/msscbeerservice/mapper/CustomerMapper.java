package com.ashish.msscbeerservice.mapper;

import com.ashish.msscbeerservice.entity.Customer;
import com.ashish.msscbeerservice.web.v1.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper//(componentModel = "spring")
public interface CustomerMapper {

    Customer convertCustomerDtoToCustomer(CustomerDto customerDto);
    CustomerDto convertCustomerToCustomerDto(Customer customer);
}
