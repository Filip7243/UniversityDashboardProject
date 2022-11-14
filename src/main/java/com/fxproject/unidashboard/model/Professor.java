package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Professor extends UniversityMember {
    @Enumerated(EnumType.STRING)
    private AcademicTitle academicTitle;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Subject> subjects; // subjects that this professor teaches
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Year> years; // years that this professor teaches

    public Professor() {
    }

    public Professor(AcademicTitle academicTitle, Set<Subject> subjects, Set<Year> years) {
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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Year> getYears() {
        return years;
    }

    public void setYears(Set<Year> years) {
        this.years = years;
    }
}
