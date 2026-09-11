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
public class SpectrumLicense extends BaseClass{
    private String bandName;
    private Double frequencyMhz;
    private Date issueDate;
    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    private LicenseStatus status;

    @ManyToOne
    private Operator operator;
}
