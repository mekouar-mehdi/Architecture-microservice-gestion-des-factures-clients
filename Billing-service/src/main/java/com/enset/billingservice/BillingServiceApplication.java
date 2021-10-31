package com.enset.billingservice;

import com.enset.billingservice.dto.InvoiceRequestDTO;
import com.enset.billingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98200), "C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(980), "C02"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(9820), "C03"));
        };
    }
}
