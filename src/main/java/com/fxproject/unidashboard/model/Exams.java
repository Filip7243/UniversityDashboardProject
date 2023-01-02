package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Exams {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "exam_id")
    private Long id;
    private String examName;
    private LocalDateTime examDate;
    @Enumerated(EnumType.STRING)
    private ExamTypes examType;
    @ManyToOne(fetch = FetchType.EAGER)
    private Groups group;
    @ManyToOne(fetch = FetchType.EAGER)
    private Subjects subject;

    public Exams() {
    }

    public Exams(Long id, String examName, LocalDateTime examDate, ExamTypes examType, Groups group, Subjects subject) {
        this.id = id;
        this.examName = examName;
        this.examDate = examDate;
        this.examType = examType;
        this.group = group;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public LocalDateTime getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDateTime examDate) {
        this.examDate = examDate;
    }

    public ExamTypes getExamType() {
        return examType;
    }

    public void setExamType(ExamTypes examType) {
        this.examType = examType;
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
