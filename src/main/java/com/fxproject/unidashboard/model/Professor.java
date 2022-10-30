package com.fxproject.unidashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private String universityEmail; // @uni.edu.pl
    private LocalDateTime dateOfBirth;
    private String placeOfBirth;
    private String phoneNumber;
    private String pesel;
    private LocalDateTime dateOfEmployment;
    private LocalDateTime createdAt; // time when student acc was created
    private Boolean isEnabled; // if not enabled, can't login to system

}
