package com.fxproject.unidashboard.dto;

import com.fxproject.unidashboard.model.UniversityMember;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class WageDto {

    private Double salary;
    private UniversityMember employee;
    private Double hourlyRate;
    private Integer hoursWorked;
    private LocalDateTime payday;

    public WageDto(Double salary, UniversityMember employee, Double hourlyRate, Integer hoursWorked, LocalDateTime payday) {
        this.salary = salary;
        this.employee = employee;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.payday = payday;
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
