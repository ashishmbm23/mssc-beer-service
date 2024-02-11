package com.ashish.msscbeerservice.mapper;

import com.ashish.msscbeerservice.entity.Customer;
import com.ashish.msscbeerservice.web.v1.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper//(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    Customer convertCustomerDtoToCustomer(CustomerDto customerDto);
    CustomerDto convertCustomerToCustomerDto(Customer customer);
}
