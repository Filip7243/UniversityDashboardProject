package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Professor extends UniversityMember implements Serializable {
    @Enumerated(EnumType.STRING)
    private AcademicTitle academicTitle;

    public Professor() {
    }

    public Professor(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

}
