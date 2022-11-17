package com.fxproject.unidashboard.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class UniversityAccount {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String universityEmail;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt; // = dateOfEmployment/startingStudies
    private Boolean isEnabled;

    public UniversityAccount() {
    }

    public UniversityAccount(Long id, String universityEmail, Role role, String password, LocalDateTime createdAt, Boolean isEnabled) {
        this.id = id;
        this.universityEmail = universityEmail;
        this.role = role;
        this.password = password;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
