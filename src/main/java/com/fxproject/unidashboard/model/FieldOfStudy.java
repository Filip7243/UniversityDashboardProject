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
    @ManyToOne
    private UniversityDepartment department;

    public FieldOfStudy() {
    }

    public FieldOfStudy(Long id, String name, TypeOfStudy type, UniversityDepartment department) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public TypeOfStudy getType() {
        return type;
    }

    public void setType(TypeOfStudy type) {
        this.type = type;
    }

    public UniversityDepartment getDepartment() {
        return department;
    }

    public void setDepartment(UniversityDepartment department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "FieldOfStudy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", department=" + department +
                '}';
    }
}
