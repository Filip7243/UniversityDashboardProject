package com.fxproject.unidashboard.dto;

import com.fxproject.unidashboard.model.Year;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class StudentDto {

    private String albumId;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private LocalDateTime dateOfBirth;
    private String placeOfBirth;
    private String phoneNumber;
    private String pesel;
    private Set<Year> years;

    public StudentDto(String albumId, String firstName, String secondName, String lastName, String email, LocalDateTime dateOfBirth, String placeOfBirth, String phoneNumber, String pesel, Set<Year> years) {
        this.albumId = albumId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.phoneNumber = phoneNumber;
        this.pesel = pesel;
        this.years = years;
    }

    public Set<Year> getYears() {
        return years;
    }

    public void setYears(Set<Year> years) {
        this.years = years;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
}
