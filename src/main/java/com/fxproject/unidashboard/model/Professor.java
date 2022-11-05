package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Professor extends UniversityEmployee{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDateTime dateOfEmployment; // todo: account table
    private LocalDateTime createdAt; // time when student acc was created
    private Boolean isEnabled; // if not enabled, can't login to system
    @Enumerated(EnumType.STRING)
    private AcademicTitle academicTitle;
    @ManyToMany
    private List<Subject> subjects; // subjects that this professor teaches
    @ManyToMany
    private List<Year> years; // years that this professor teaches

    public Professor() {
    }

    public Professor(String name, String secondName, String lastName, String email, String universityEmail, LocalDateTime dateOfBirth, String placeOfBirth, String phoneNumber, String pesel, Long id, LocalDateTime dateOfEmployment, LocalDateTime createdAt, Boolean isEnabled, AcademicTitle academicTitle, List<Subject> subjects, List<Year> years) {
        super(name, secondName, lastName, email, universityEmail, dateOfBirth, placeOfBirth, phoneNumber, pesel);
        this.id = id;
        this.dateOfEmployment = dateOfEmployment;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
        this.academicTitle = academicTitle;
        this.subjects = subjects;
        this.years = years;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public LocalDateTime getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDateTime dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", dateOfEmployment=" + dateOfEmployment +
                '}';
    }
}
