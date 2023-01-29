package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Marks {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mark_id")
    private Long id;
    private Double mark;
    private LocalDate markDate;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private Students student;
    @ManyToOne(fetch = FetchType.EAGER)
    private Subjects subject;
    @Enumerated(EnumType.STRING)
    private ExamTypes type;

    public Marks() {
    }

    public Marks(Long id, Double mark, LocalDate markDate,
                 String description, Students student, Subjects subject, ExamTypes type) {
        this.id = id;
        this.mark = mark;
        this.markDate = markDate;
        this.description = description;
        this.student = student;
        this.subject = subject;
        this.type = type;
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

    public LocalDate getMarkDate() {
        return markDate;
    }

    public void setMarkDate(LocalDate markDate) {
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

    public ExamTypes getType() {
        return type;
    }

    public void setType(ExamTypes type) {
        this.type = type;
    }
}
