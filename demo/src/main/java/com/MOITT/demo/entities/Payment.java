package com.MOITT.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Payment extends BaseClass{
    private Double amount;
    private String method;
    private Date paidDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    private Application application;
}
