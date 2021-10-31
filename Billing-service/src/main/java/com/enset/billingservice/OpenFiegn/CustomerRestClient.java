package com.enset.billingservice.OpenFiegn;

import com.enset.billingservice.entities.InvoiceEntity;
import com.enset.billingservice.model.CustomerModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping(path = "/api/customers/{id}")
    public CustomerModel getCustomer(@PathVariable(value = "id") String id);

    @GetMapping(path = "/api/customers/all")
    public List<CustomerModel> allCustomers();

}
