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
public class Department extends BaseClass{
    private String name;
    private String description;

    @ManyToOne
    private Ministry ministry;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Officer>officers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Service> services;
}
