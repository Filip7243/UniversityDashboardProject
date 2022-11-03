package com.fxproject.unidashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.time.LocalDateTime;
import java.util.List;

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
    private String universityEmail; // @stud.uni.edu.pl
    private LocalDateTime dateOfBirth;
    private String placeOfBirth;
    private String phoneNumber;
    private String pesel;
    //    private LocalDateTime createdAt; // time when student acc was created todo: account table
    private Boolean isEnabled; // if not enabled, student can't login to system
    @ManyToMany
    private List<Year> years; // many students can attend on many years

    public Student() {
    }

    public Student(Long id, String albumId, String firstName, String secondName, String lastName, String email, String universityEmail, LocalDateTime dateOfBirth, String placeOfBirth, String phoneNumber, String pesel, Boolean isEnabled, List<Year> years) {
        this.id = id;
        this.albumId = albumId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.universityEmail = universityEmail;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.phoneNumber = phoneNumber;
        this.pesel = pesel;
        this.isEnabled = isEnabled;
        this.years = years;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversityEmail() {
        return universityEmail;
    }

    public void setUniversityEmail(String universityEmail) {
        this.universityEmail = universityEmail;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public List<Year> getFieldsOfStudy() {
        return years;
    }

    public void setFieldsOfStudy(List<Year> years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", albumId='" + albumId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", universityEmail='" + universityEmail + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                '}';
    }
}
