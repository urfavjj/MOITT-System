package com.MOITT.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Application extends BaseClass{
    private Date applicationDate;
    private String referenceNumber;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    private Citizen citizen;

    @ManyToOne
    private Service service;

    @ManyToOne
    private Officer officer;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;
}
