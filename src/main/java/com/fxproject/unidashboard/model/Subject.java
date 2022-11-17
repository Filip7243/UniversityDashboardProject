package com.fxproject.unidashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private Set<Professor> professors;

    public Subject() {
    }

    public Subject(Long id, String name, Set<Professor> professors) {
        this.id = id;
        this.name = name;
        this.professors = professors;
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

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }
}
