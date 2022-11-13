package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Year { // konkretny rocznik, danego kierunku

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name; // "2021/2025"(3.5 lat inżynierka)
    @ManyToMany
    private List<Student> students;
    @ManyToOne
    private FieldOfStudy fieldOfStudy;
    private Integer yearOfStudy;
    private LocalDateTime startedStudies;
    private LocalDateTime expectedYearOfEndStudies; // todo: delete
    @ManyToMany
    private List<Subject> subjects;
    //todo; timetable, sylabus


    public Year() {
    }

    public Year(Long id, String name, List<Student> students, FieldOfStudy fieldOfStudy, Integer yearOfStudy, LocalDateTime startedStudies, LocalDateTime expectedYearOfEndStudies, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.fieldOfStudy = fieldOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.startedStudies = startedStudies;
        this.expectedYearOfEndStudies = expectedYearOfEndStudies;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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

    public LocalDateTime getExpectedYearOfEndStudies() {
        return expectedYearOfEndStudies;
    }

    public void setExpectedYearOfEndStudies(LocalDateTime expectedYearOfEndStudies) {
        this.expectedYearOfEndStudies = expectedYearOfEndStudies;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
