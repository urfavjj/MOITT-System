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
public class Milestone extends BaseClass{
    private String title;
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private MilestoneStatus status;


    @ManyToOne
    private Project project;
}
