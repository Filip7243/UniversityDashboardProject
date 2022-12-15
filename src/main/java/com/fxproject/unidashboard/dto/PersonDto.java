package com.fxproject.unidashboard.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;

public class PersonDto extends RecursiveTreeObject<PersonDto> {

    private String firstName;
    private String lastName;
    private String email;
    private String universityEmail;
    private String albumId;
    private String phoneNumber;
    private String pesel;
    private String role;
    private SimpleBooleanProperty isEnable;
    // more info in PersonDetailsDto


    public PersonDto() {
    }

    public PersonDto(String firstName, String lastName, String email, String universityEmail, String albumId, String phoneNumber, String pesel, String role,
                     Boolean isEnable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.universityEmail = universityEmail;
        this.albumId = albumId;
        this.phoneNumber = phoneNumber;
        this.pesel = pesel;
        this.role = role;
        this.isEnable = new SimpleBooleanProperty(isEnable);
    }

    public boolean isIsEnable() {
        return isEnable.get();
    }

    public SimpleBooleanProperty isEnableProperty() {
        return isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable.set(isEnable);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
