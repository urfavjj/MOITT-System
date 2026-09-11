package com.MOITT.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Officer extends BaseClass{
    private String name;
    private String email;
    private String phoneNumber;
    private String designation;

    @ManyToOne
    private Department department;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Application> applications;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Inspection> inspections;
}
