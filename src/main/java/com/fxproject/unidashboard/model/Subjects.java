package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Subjects {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "subject_id")
    private Long id;
    private String name;
    @OneToMany(
            mappedBy = "subject",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProfessorsSubjectsInGroups> psig = new ArrayList<>();

    public Subjects() {
    }

    public Subjects(Long id, String name) {
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
        return name;
    }
}
