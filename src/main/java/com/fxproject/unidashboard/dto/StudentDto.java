package com.fxproject.unidashboard.dto;

public class StudentDto {

    private Long id;
    private String albumId; // this is id that student uses (generate value)
    private String firstName;
    private String secondName; // optional
    private String lastName;
    private String email;
    private String universityEmail; // @stud.uni.edu.pl
    private String phoneNumber;
    private String pesel;

    public StudentDto(Long id, String albumId, String firstName, String secondName, String lastName, String email, String universityEmail, String phoneNumber, String pesel) {
        this.id = id;
        this.albumId = albumId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.universityEmail = universityEmail;
        this.phoneNumber = phoneNumber;
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
               ", albumId='" + albumId + '\'' +
               ", firstName='" + firstName + '\'' +
               ", secondName='" + secondName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", universityEmail='" + universityEmail + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", pesel='" + pesel + '\'' +
               '}';
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
