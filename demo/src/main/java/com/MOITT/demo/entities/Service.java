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
public class Service extends BaseClass{
    private String name;
    private String description;
    private Double fee;
    private Integer processingDays;

    @ManyToOne
    private Department department;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Application> applications;

}
