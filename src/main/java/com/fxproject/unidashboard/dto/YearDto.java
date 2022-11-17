package com.fxproject.unidashboard.dto;

import com.fxproject.unidashboard.model.FieldOfStudy;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.model.Subject;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;
import java.util.Set;

public class YearDto {
    private String name;
    private FieldOfStudy fieldOfStudy;
    private Integer yearOfStudy;
    private Set<Subject> subjects;

    public YearDto(String name, FieldOfStudy fieldOfStudy, Integer yearOfStudy, Set<Subject> subjects) {
        this.name = name;
        this.fieldOfStudy = fieldOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.subjects = subjects;
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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
