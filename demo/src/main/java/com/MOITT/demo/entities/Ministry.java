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
public class Ministry extends BaseClass{
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Department> departments;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Project>projects;
}
