package com.enset.customerservice.mappers;

import com.enset.customerservice.dto.CustomerRequestDTO;
import com.enset.customerservice.dto.CustomerResponseDTO;
import com.enset.customerservice.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDTO customerToCustomerResponseDTO(CustomerEntity customerEntity);
    CustomerEntity customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);

}
