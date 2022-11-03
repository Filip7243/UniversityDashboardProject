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


    public UniversityDepartment() {
    }
    public UniversityDepartment(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "UniversityDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
