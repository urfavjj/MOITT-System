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
public class Citizen extends BaseClass{
    private String name;
    private String nationalId;
    private String phoneNumber;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Application> applications;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Complaint> complaints;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DomainRegistration> domainRegistrations;
}
