package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
public class UniversityAccounts {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "account_id")
    private Long id;
    private String universityEmail;
    private String password;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @OneToOne(optional = false)
    private Person person;
    @Enumerated(STRING)
    private Roles role;

    public UniversityAccounts() {
    }

    public UniversityAccounts(String universityEmail, String password, LocalDateTime createdAt, Boolean isEnabled, Person person, Roles role) {
        this.universityEmail = universityEmail;
        this.password = password;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
        this.person = person;
        this.role = role;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles roles) {
        this.role = roles;
    }


}
