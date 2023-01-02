package com.fxproject.unidashboard.dto;

import java.time.LocalDate;

public class StudentAttendanceOnLecture {

    private String subjectName;
    private LocalDate date;
    private String isPresent;

    public StudentAttendanceOnLecture(String subjectName, LocalDate date, String isPresent) {
        this.subjectName = subjectName;
        this.date = date;
        this.isPresent = isPresent;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(String present) {
        isPresent = present;
    }
}
