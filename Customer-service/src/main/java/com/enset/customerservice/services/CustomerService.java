package com.enset.customerservice.services;

import com.enset.customerservice.dto.CustomerRequestDTO;
import com.enset.customerservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    public CustomerResponseDTO getCustomer(String id);
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
    public List<CustomerResponseDTO> listCustomers();
}
