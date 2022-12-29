package com.fxproject.unidashboard.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "ProfessorsSubjectsInGroups")
@Table(name = "PROFESSORSSUBJECTSINGROUPS")
public class ProfessorsSubjectsInGroups {

    @EmbeddedId
    private PSIGID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("professorId")
    private Professors professor;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("subjectId")
    private Subjects subject;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("groupId")
    private Groups group;

    public ProfessorsSubjectsInGroups() {
    }

    public ProfessorsSubjectsInGroups(Professors professor, Subjects subject, Groups group) {
        this.professor = professor;
        this.subject = subject;
        this.group = group;
        this.id = new PSIGID(professor.getId(), subject.getId(), group.getId());
    }

    public PSIGID getId() {
        return id;
    }

    public Professors getProfessor() {
        return professor;
    }

    public Subjects getSubject() {
        return subject;
    }

    public Groups getGroup() {
        return group;
    }

    public void setId(PSIGID id) {
        this.id = id;
    }

    public void setProfessor(Professors professor) {
        this.professor = professor;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorsSubjectsInGroups that = (ProfessorsSubjectsInGroups) o;
        return id.equals(that.id) && professor.equals(that.professor) && subject.equals(that.subject) && group.equals(that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, professor, subject, group);
    }
}
