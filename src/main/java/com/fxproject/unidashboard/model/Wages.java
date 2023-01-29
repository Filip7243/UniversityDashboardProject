package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Wages {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "wage_id")
    private Long id;
    private Double salary;
    private Double hourlyRate;
    private Double hoursWorked;
    private LocalDate payday;
    @ManyToOne(fetch = FetchType.EAGER)
    private Professors professor;

    public Wages() {
    }

    public Wages(Long id, Double salary, Double hourlyRate,
                 Double hoursWorked, LocalDate payday, Professors professor) {
        this.id = id;
        this.salary = salary;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.payday = payday;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public LocalDate getPayday() {
        return payday;
    }

    public void setPayday(LocalDate payday) {
        this.payday = payday;
    }

    public Professors getProfessor() {
        return professor;
    }

    public void setProfessor(Professors professor) {
        this.professor = professor;
    }
}
