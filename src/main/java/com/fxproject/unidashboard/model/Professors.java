package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class Professors extends Person {

    private Long albumId;
    @Enumerated(STRING)
    private AcademicTitles academicTitles;
    @OneToMany(
            mappedBy = "professor",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<ProfessorsSubjectsInGroups> psig = new ArrayList<>();

    public Professors() {
    }

    public Professors(AcademicTitles academicTitles) {
        this.academicTitles = academicTitles;
    }

    public Professors(Long id, String firstName, String lastName, String email, String phoneNumber,
                       LocalDateTime birthday, String placeOfBirth, String pesel, Character gender,
                       Integer age, Addresses address, AcademicTitles academicTitles) {
        super(id, firstName, lastName, email, phoneNumber, birthday, placeOfBirth, pesel, gender, age, address);
        this.academicTitles = academicTitles;
    }

    public AcademicTitles getAcademicTitle() {
        return academicTitles;
    }

    public void setAcademicTitle(AcademicTitles academicTitles) {
        this.academicTitles = academicTitles;
    }

    public AcademicTitles getAcademicTitles() {
        return academicTitles;
    }

    public void setAcademicTitles(AcademicTitles academicTitles) {
        this.academicTitles = academicTitles;
    }

    public List<ProfessorsSubjectsInGroups> getPsig() {
        return psig;
    }

    public void setPsig(List<ProfessorsSubjectsInGroups> psig) {
        this.psig = psig;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}
