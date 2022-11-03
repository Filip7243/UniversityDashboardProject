package com.fxproject.unidashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class UniversityDepartment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    //todo; maybe @OneToMany relation with FieldOfStudies
}
