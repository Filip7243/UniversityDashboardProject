package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.util.function.BinaryOperator;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Attendances {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "attendance_id")
    private Long id;
    private Boolean isPresent;
    private Boolean isLate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Lectures lecture;
    @ManyToOne(fetch = FetchType.LAZY)
    private Students student;

    public Attendances() {
    }

    public Attendances(Long id, Boolean isPresent, Boolean isLate, Lectures lecture, Students student) {
        this.id = id;
        this.isPresent = isPresent;
        this.isLate = isLate;
        this.lecture = lecture;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    public Boolean getLate() {
        return isLate;
    }

    public void setLate(Boolean late) {
        isLate = late;
    }

    public Lectures getLecture() {
        return lecture;
    }

    public void setLecture(Lectures lecture) {
        this.lecture = lecture;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }
}
