package com.MOITT.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Document extends BaseClass{
    private String title;
    private String type;
    private Date uploadDate;

    @ManyToOne
    private Application application;

    @ManyToOne
    private Project project;
}
