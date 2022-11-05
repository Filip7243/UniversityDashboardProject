package com.fxproject.unidashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Student extends UniversityMember {

    private String albumId; // this is id that student uses (generate value)
    @ManyToMany
    private List<Year> years; // many students can attend to many years

    public Student() {
    }

    public Student(Boolean isEnabled, List<Year> years) {
        this.albumId = albumId;
        this.years = years;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public List<Year> getYears() {
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }

}
