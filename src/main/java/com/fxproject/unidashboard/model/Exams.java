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
    @ManyToOne(fetch = FetchType.LAZY)
    private Groups group;
    @ManyToOne(fetch = FetchType.LAZY)
    private Subjects subject;
}
