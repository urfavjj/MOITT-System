package com.MOITT.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Inspection extends BaseClass{
    private Date inspectionDate;
    private String result;
    private String notes;

    @ManyToOne
    private Operator operator;

    @ManyToOne
    private Officer officer;
}
