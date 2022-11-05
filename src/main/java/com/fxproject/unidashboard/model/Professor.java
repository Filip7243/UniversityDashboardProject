package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Professor extends UniversityMember {

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

    public Professor(LocalDateTime dateOfEmployment, LocalDateTime createdAt, Boolean isEnabled, AcademicTitle academicTitle, List<Subject> subjects, List<Year> years) {
        this.dateOfEmployment = dateOfEmployment;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
        this.academicTitle = academicTitle;
        this.subjects = subjects;
        this.years = years;
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
                ", dateOfEmployment=" + dateOfEmployment +
                '}';
    }
}
