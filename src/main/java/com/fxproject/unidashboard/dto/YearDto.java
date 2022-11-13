package com.fxproject.unidashboard.dto;

import com.fxproject.unidashboard.model.FieldOfStudy;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.model.Subject;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

public class YearDto {
    private String name;
    private List<Student> students;
    private FieldOfStudy fieldOfStudy;
    private Integer yearOfStudy;
    private List<Subject> subjects;

    public YearDto(String name, List<Student> students, FieldOfStudy fieldOfStudy, Integer yearOfStudy, List<Subject> subjects) {
        this.name = name;
        this.students = students;
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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
