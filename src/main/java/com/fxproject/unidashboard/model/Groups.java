package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "groups")
public class Groups {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "group_id")
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private FieldsOfStudy fieldOfStudy;
    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProfessorsSubjectsInGroups> psig = new ArrayList<>();

    public Groups() {
    }

    public Groups(Long id, String name, FieldsOfStudy fieldOfStudy) {
        this.id = id;
        this.name = name;
        this.fieldOfStudy = fieldOfStudy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldsOfStudy getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(FieldsOfStudy fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public List<ProfessorsSubjectsInGroups> getPsig() {
        return psig;
    }

    public void setPsig(List<ProfessorsSubjectsInGroups> psig) {
        this.psig = psig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups = (Groups) o;
        return id.equals(groups.id) && name.equals(groups.name) && fieldOfStudy.equals(groups.fieldOfStudy) && psig.equals(groups.psig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fieldOfStudy, psig);
    }

    @Override
    public String toString() {
        return name;
    }
}
