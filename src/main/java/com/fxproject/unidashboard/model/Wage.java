package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Wage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Double salary;
    @ManyToOne
    private UniversityMember employee;
    private Double hourlyRate;
    private Integer hoursWorked;
    private LocalDateTime payday;

    public Wage(Long id, Double salary, UniversityMember employee, Double hourlyRate, Integer hoursWorked, LocalDateTime payday) {
        this.id = id;
        this.salary = salary;
        this.employee = employee;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.payday = payday;
    }

    public Wage() {
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

    public UniversityMember getEmployee() {
        return employee;
    }

    public void setEmployee(UniversityMember employee) {
        this.employee = employee;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public LocalDateTime getPayday() {
        return payday;
    }

    public void setPayday(LocalDateTime payday) {
        this.payday = payday;
    }
}
