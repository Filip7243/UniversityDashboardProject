package com.fxproject.unidashboard.dto;

public class GradeStudentDto {

    private String description;
    private Double grade;
    private Long studentId;
    private Long subjectId;
    private Long professorId;

    public GradeStudentDto(String description, Double grade, Long studentId, Long subjectId, Long professorId) {
        this.description = description;
        this.grade = grade;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.professorId = professorId;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}
