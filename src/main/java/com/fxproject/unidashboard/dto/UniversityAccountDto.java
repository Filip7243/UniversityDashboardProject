package com.fxproject.unidashboard.dto;

import com.fxproject.unidashboard.model.Role;
import com.fxproject.unidashboard.model.UniversityMember;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class UniversityAccountDto {

    private UniversityMember member;
    private String universityEmail;
    private String password;
    private Role role;
    private LocalDateTime createdAt; // = dateOfEmployment/startingStudies
    private Boolean isEnabled;

    public UniversityAccountDto(UniversityMember member, String universityEmail, String password, Role role, LocalDateTime createdAt, Boolean isEnabled) {
        this.member = member;
        this.universityEmail = universityEmail;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
    }

    public UniversityMember getMember() {
        return member;
    }

    public void setMember(UniversityMember member) {
        this.member = member;
    }

    public String getUniversityEmail() {
        return universityEmail;
    }

    public void setUniversityEmail(String universityEmail) {
        this.universityEmail = universityEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
