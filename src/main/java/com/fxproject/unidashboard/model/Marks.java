package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Marks {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mark_id")
    private Long id;
    private Double mark;
    private LocalDateTime markDate;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Students student;
    @ManyToOne(fetch = FetchType.LAZY)
    private Subjects subject;

    public Marks() {
    }

    public Marks(Long id, Double mark, LocalDateTime markDate,
                 String description, Students student, Subjects subject) {
        this.id = id;
        this.mark = mark;
        this.markDate = markDate;
        this.description = description;
        this.student = student;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public LocalDateTime getMarkDate() {
        return markDate;
    }

    public void setMarkDate(LocalDateTime markDate) {
        this.markDate = markDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }
}
