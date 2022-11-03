package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class FieldOfStudy {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TypeOfStudy type; // inżynierskie, licencjackie, magisterskie
    private Integer duration;
    @ManyToOne
    private UniversityDepartment department;

}
