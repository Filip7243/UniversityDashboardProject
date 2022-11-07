package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Wage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Double salary;
    @ManyToOne
    private UniversityMember employee;
    private Double hourlyRate;
    private Integer hoursWorked;
    private LocalDateTime payday;

}
