package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class FieldsOfStudy {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "field_of_study_id")
    private Long id;
    private String name;
    @Enumerated(STRING)
    private TypesOfStudy typeOfStudy;
    @ManyToOne(fetch = FetchType.LAZY)
    private Departments department;

    public FieldsOfStudy() {
    }

    public FieldsOfStudy(Long id, String name, TypesOfStudy typeOfStudy, Departments department) {
        this.id = id;
        this.name = name;
        this.typeOfStudy = typeOfStudy;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypesOfStudy getTypeOfStudy() {
        return typeOfStudy;
    }

    public void setTypeOfStudy(TypesOfStudy typeOfStudy) {
        this.typeOfStudy = typeOfStudy;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }
}
