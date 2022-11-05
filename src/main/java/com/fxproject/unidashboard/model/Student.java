package com.fxproject.unidashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Student extends UniversityMember {

    private String albumId; // this is id that student uses (generate value)
    //    private LocalDateTime createdAt; // time when student acc was created todo: account table
    private Boolean isEnabled; // if not enabled, student can't login to system
    @ManyToMany
    private List<Year> years; // many students can attend to many years

    public Student() {
    }

    public Student(Boolean isEnabled, List<Year> years) {
        this.albumId = albumId;
        this.isEnabled = isEnabled;
        this.years = years;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
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

}
