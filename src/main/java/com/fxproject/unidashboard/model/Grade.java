package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String description;
    private Double grade; // from 2.0 to 5.0
    @ManyToOne
    private Student student;
    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Subject subject;
    //todo; type (exam, kolos etc.)


    public Grade() {
    }

    public Grade(Long id, String description, Double grade, Student student, Professor professor, Subject subject) {
        this.id = id;
        this.description = description;
        this.grade = grade;
        this.student = student;
        this.professor = professor;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
