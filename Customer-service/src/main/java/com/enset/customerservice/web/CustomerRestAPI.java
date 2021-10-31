package com.enset.customerservice.web;

import com.enset.customerservice.dto.CustomerRequestDTO;
import com.enset.customerservice.dto.CustomerResponseDTO;
import com.enset.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/customers")
public class CustomerRestAPI {
    private CustomerService customerService;

    public CustomerRestAPI(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/all")
    public List<CustomerResponseDTO> allCustomers() {
        return customerService.listCustomers();
    }

    @PostMapping(path = "/add")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        customerRequestDTO.setId(UUID.randomUUID().toString());
        return customerService.save(customerRequestDTO);
    }

    @GetMapping(path = "/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable(value = "id") String id){
        return customerService.getCustomer(id);
    }




}
