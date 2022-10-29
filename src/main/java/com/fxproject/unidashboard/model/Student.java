package com.fxproject.unidashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id; // this is ID for db
    private String albumId; // this is id that student uses (generate value)
    private String firstName;
    private String secondName; // optional
    private String lastName;
    private String email;
    private String universityEmail;
    private LocalDateTime dateOfBirth;
    private String placeOfBirth;
    private String phoneNumber;
    private String pesel;
    private LocalDateTime dateOfStartStudies;
    private LocalDateTime createdAt; // time when student acc was created
    private Boolean isEnabled; // if not student can't login to system
    private Long parentId; // foreign key from Parent entity

}
