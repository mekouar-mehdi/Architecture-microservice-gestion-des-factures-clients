package com.enset.billingservice.services;

import com.enset.billingservice.dto.InvoiceRequestDTO;
import com.enset.billingservice.dto.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {

    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    public InvoiceResponseDTO getInvoice(String id);
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId);
    public List<InvoiceResponseDTO> allInvoices();


}
