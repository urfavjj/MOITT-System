package com.MOITT.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Complaint extends BaseClass{
    private String subject;
    private String description;
    private Date filedDate;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    @ManyToOne
    private Citizen citizen;

    @ManyToOne
    private Operator operator;

    @ManyToOne
    private Officer officer;
}
