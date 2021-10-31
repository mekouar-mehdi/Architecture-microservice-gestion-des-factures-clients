package com.enset.billingservice.services.impl;

import com.enset.billingservice.OpenFiegn.CustomerRestClient;
import com.enset.billingservice.dto.InvoiceRequestDTO;
import com.enset.billingservice.dto.InvoiceResponseDTO;
import com.enset.billingservice.entities.InvoiceEntity;
import com.enset.billingservice.exceptions.CustomerNotFoundException;
import com.enset.billingservice.mappers.InvoiceMapper;
import com.enset.billingservice.model.CustomerModel;
import com.enset.billingservice.repositories.InvoiceRepository;
import com.enset.billingservice.services.InvoiceService;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;



    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }


    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        CustomerModel customer=null;
        try {
            customer = customerRestClient.getCustomer(invoiceRequestDTO.getClientId());
        } catch(Exception ex) {
            throw new CustomerNotFoundException("Customer not found !!");

        }

        InvoiceEntity invoiceEntity = invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoiceEntity.setId(UUID.randomUUID().toString());
        invoiceEntity.setDate(new Date());
        /*
        * verifier les conditions d'integrité referentielinvoice / customer

        * */
        InvoiceEntity saveInvoice = invoiceRepository.save(invoiceEntity);

//      pour attacher le client avec sa facture----
        saveInvoice.setCustomer(customer);

        return invoiceMapper.formInvoice(saveInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String id) {
        System.out.println("invoice id service => " + id);
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id).get();
        CustomerModel customer = customerRestClient.getCustomer(invoiceEntity.getClientId());
        invoiceEntity.setCustomer(customer);
        return invoiceMapper.formInvoice(invoiceEntity);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {
        List<InvoiceEntity> invoices = invoiceRepository.findByClientId(customerId);
        invoices.forEach(inv -> {
            CustomerModel customer = customerRestClient.getCustomer(inv.getClientId());
            inv.setCustomer(customer);
        });
        return invoices.stream().map(invoice ->
            invoiceMapper.formInvoice(invoice))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {

        List<InvoiceEntity> invoices = invoiceRepository.findAll();
        invoices.forEach(inv -> {
            CustomerModel customer = customerRestClient.getCustomer(inv.getClientId());
            inv.setCustomer(customer);
        });

        return invoices.stream().map(invoice ->
                        invoiceMapper.formInvoice(invoice))
                .collect(Collectors.toList());
    }
}
