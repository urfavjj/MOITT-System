package com.MOITT.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class DomainRegistration extends BaseClass{
    private String domainName;
    private Date registeredDate;
    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    private DomainStatus status;

    @ManyToOne
    private Citizen citizen;
}
