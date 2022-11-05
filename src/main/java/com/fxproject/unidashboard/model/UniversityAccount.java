package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class UniversityAccount {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToOne
    private UniversityMember member;
    private String universityEmail;
    private String password;
    private LocalDateTime createdAt; // = dateOfEmployment/startingStudies
    private Boolean isEnabled;

    public UniversityAccount() {
    }

    public UniversityAccount(Long id, UniversityMember member, String universityEmail, String password, LocalDateTime createdAt, Boolean isEnabled) {
        this.id = id;
        this.member = member;
        this.universityEmail = universityEmail;
        this.password = password;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
