package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Departments {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "department_id")
    private Long id;
    private String name;

    public Departments() {
    }

    public Departments(Long id, String name) {
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
}
