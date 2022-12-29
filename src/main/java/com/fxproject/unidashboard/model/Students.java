package com.fxproject.unidashboard.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Students extends Person {

    private Long albumId;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Groups> groups;

    public Students() {
        String lUUID = String.format("%040d",
                new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        this.albumId = Long.parseLong(lUUID.substring(0, 7));
        this.groups = new HashSet<>();
    }

    public Students(Long id, String firstName, String lastName, String email, String phoneNumber, LocalDateTime birthday,
                    String placeOfBirth, String pesel, Character gender, Integer age, Addresses address) {
        super(id, firstName, lastName, email, phoneNumber, birthday, placeOfBirth, pesel, gender, age, address);
        String lUUID = String.format("%040d",
                new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        this.albumId = Long.parseLong(lUUID.substring(0, 7));

        this.groups = new HashSet<>();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Set<Groups> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }
}
