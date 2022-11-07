package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Professor extends UniversityMember {
    @Enumerated(EnumType.STRING)
    private AcademicTitle academicTitle;
    @ManyToMany
    private List<Subject> subjects; // subjects that this professor teaches
    @ManyToMany
    private List<Year> years; // years that this professor teaches

    public Professor() {
    }

    public Professor(AcademicTitle academicTitle, List<Subject> subjects, List<Year> years) {
        this.academicTitle = academicTitle;
        this.subjects = subjects;
        this.years = years;
    }

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Year> getYears() {
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }
}
