package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Lectures {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "lecture_id")
    private Long id;
    private String lectureTopic;
    private LocalDateTime lectureDate;
    @ManyToOne(fetch = EAGER)
    private Groups group;
    @ManyToOne(fetch = EAGER)
    private Subjects subject;

    public Lectures() {
    }

    public Lectures(Long id, String lectureTopic, LocalDateTime lectureDate, Groups group, Subjects subject) {
        this.id = id;
        this.lectureTopic = lectureTopic;
        this.lectureDate = lectureDate;
        this.group = group;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLectureTopic() {
        return lectureTopic;
    }

    public void setLectureTopic(String lectureTopic) {
        this.lectureTopic = lectureTopic;
    }

    public LocalDateTime getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(LocalDateTime lectureDate) {
        this.lectureDate = lectureDate;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }
}
