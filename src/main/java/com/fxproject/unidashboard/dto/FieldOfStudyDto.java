package com.fxproject.unidashboard.dto;

import com.fxproject.unidashboard.model.TypeOfStudy;
import com.fxproject.unidashboard.model.UniversityDepartment;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

public class FieldOfStudyDto {

    private String name;
    @Enumerated(EnumType.STRING)
    private TypeOfStudy type; // inżynierskie, licencjackie, magisterskie
    @ManyToOne
    private UniversityDepartment department;

    public FieldOfStudyDto(String name, TypeOfStudy type, UniversityDepartment department) {
        this.name = name;
        this.type = type;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOfStudy getType() {
        return type;
    }

    public void setType(TypeOfStudy type) {
        this.type = type;
    }

    public UniversityDepartment getDepartment() {
        return department;
    }

    public void setDepartment(UniversityDepartment department) {
        this.department = department;
    }
}
