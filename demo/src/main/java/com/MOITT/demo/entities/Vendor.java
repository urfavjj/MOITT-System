package com.MOITT.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Vendor extends BaseClass{
    private String name;
    private String contactEmail;
    private String phoneNumber;
    private String country;

    @ManyToMany
    private List<Project> projects;
}
