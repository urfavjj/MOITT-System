package com.MOITT.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Project extends BaseClass{
    private String title;
    private Double budget;
    private Date startDate;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToOne
    private Ministry ministry;

    @ManyToMany
    private List<Vendor> vendors;
}
