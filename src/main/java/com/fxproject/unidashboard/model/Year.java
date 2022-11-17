package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Year { // konkretny rocznik, danego kierunku

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name; // "2021/2025"(3.5 lat inżynierka)
    @ManyToOne
    private FieldOfStudy fieldOfStudy;
    private Integer yearOfStudy;
    private LocalDateTime startedStudies;
    @ManyToMany
    private Set<Subject> subjects;
    //todo; timetable, sylabus


    public Year() {
    }

    public Year(Long id, String name, Set<Student> students, FieldOfStudy fieldOfStudy, Integer yearOfStudy, LocalDateTime startedStudies, Set<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.fieldOfStudy = fieldOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.startedStudies = startedStudies;
        this.subjects = subjects;
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


    public FieldOfStudy getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public LocalDateTime getStartedStudies() {
        return startedStudies;
    }

    public void setStartedStudies(LocalDateTime startedStudies) {
        this.startedStudies = startedStudies;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
