package com.enset.billingservice.web;

import com.enset.billingservice.dto.InvoiceRequestDTO;
import com.enset.billingservice.dto.InvoiceResponseDTO;
import com.enset.billingservice.exceptions.ExceptionMessage;
import com.enset.billingservice.services.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/invoices")
public class InvoiceRestAPI {

    private InvoiceService invoiceService;

    public InvoiceRestAPI(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable(name = "id") String id){
        System.out.println("invoice id => " + id);

        return invoiceService.getInvoice(id);
    }

    @GetMapping(path = "/customer/{customerId}")
    public List<InvoiceResponseDTO> getInvoicesByCustomerId(
            @PathVariable(value = "customerId") String customerId){
        return invoiceService.invoicesByCustomerId(customerId);
    }


    @GetMapping(path = "/all")
    public List<InvoiceResponseDTO> getAllInvoices(){
        return invoiceService.allInvoices();
    }

    @PostMapping(path = "/add")
    public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.save(invoiceRequestDTO);
    }

    @ExceptionHandler(Exception.class)
    public ExceptionMessage exceptionHandler(Exception e) {
        ExceptionMessage exp = new ExceptionMessage(e.getMessage());
        return exp;
    }

}
