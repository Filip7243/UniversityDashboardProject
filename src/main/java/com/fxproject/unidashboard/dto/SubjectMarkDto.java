package com.fxproject.unidashboard.dto;

public class SubjectMarkDto {

    private String subjectName;
    private String mark;

    public SubjectMarkDto(String subjectName, String mark) {
        this.subjectName = subjectName;
        this.mark = mark;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
