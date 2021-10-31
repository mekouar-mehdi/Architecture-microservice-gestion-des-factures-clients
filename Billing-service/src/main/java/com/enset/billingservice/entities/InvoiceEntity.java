package com.enset.billingservice.entities;


import com.enset.billingservice.model.CustomerModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntity {
    @Id
    private String id;
    private Date date;
    private BigDecimal amount;
    private String clientId;

    @Transient
    private CustomerModel customer;
}
