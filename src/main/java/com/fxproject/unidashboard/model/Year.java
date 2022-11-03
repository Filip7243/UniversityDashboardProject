package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Year { // konkretny rocznik, danego kierunku

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name; // "2021/2025"(3.5 lat inżynierka)
    @ManyToMany
    private List<Student> students;
    @ManyToOne
    private FieldOfStudy fieldOfStudy;
    private Integer yearOfStudy;
    private LocalDateTime startedStudies;
    private LocalDateTime expectedYearOfEndStudies;
    //todo; timetable
}
