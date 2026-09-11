package com.MOITT.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Operator extends BaseClass{
    private String name;
    private String licenseNumber;
    private String contactEmail;
    private String country;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SpectrumLicense> spectrumLicenses;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Inspection> inspections;
}
