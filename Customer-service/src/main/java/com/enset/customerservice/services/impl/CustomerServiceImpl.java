package com.enset.customerservice.services.impl;

import com.enset.customerservice.dto.CustomerRequestDTO;
import com.enset.customerservice.dto.CustomerResponseDTO;
import com.enset.customerservice.entities.CustomerEntity;
import com.enset.customerservice.mappers.CustomerMapper;
import com.enset.customerservice.repositories.CustomerRepository;
import com.enset.customerservice.services.CustomerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;



    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        CustomerEntity customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        CustomerEntity savedCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(savedCustomer);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        CustomerEntity customer = customerRepository.findById(id).get();
        return customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        CustomerEntity customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        CustomerEntity updatedCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponse = customerMapper.customerToCustomerResponseDTO((updatedCustomer));
        return customerResponse;
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        List<CustomerResponseDTO> customerRepsonseDTOS =
                customers
                .stream()
                .map(c -> customerMapper.customerToCustomerResponseDTO(c))
                .collect(Collectors.toList());
        return customerRepsonseDTOS;
    }
}
