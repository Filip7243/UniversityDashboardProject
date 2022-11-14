package com.fxproject.unidashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.List;
import java.util.Set;

@Entity
public class Student extends UniversityMember {

    private String albumId; // this is id that student uses (generate value)
    @ManyToMany
    private Set<Year> years; // many students can attend to many years

    public Student() {
    }

    public Student(Boolean isEnabled, Set<Year> years) {
        this.albumId = albumId;
        this.years = years;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public Set<Year> getYears() {
        return years;
    }

    public void setYears(Set<Year> years) {
        this.years = years;
    }

}
