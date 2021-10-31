package com.enset.customerservice;

import com.enset.customerservice.dto.CustomerRequestDTO;
import com.enset.customerservice.repositories.CustomerRepository;
import com.enset.customerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerService customerService){
        return args -> {
            customerService.save(new CustomerRequestDTO("C01", "adria", "Adria@adria.com"));
            customerService.save(new CustomerRequestDTO("C02", "openlab", "openlab@openlab.com"));
            customerService.save(new CustomerRequestDTO("C03", "confidentiel", "confidentiel@confidentiel.com"));
        };
    }



}
