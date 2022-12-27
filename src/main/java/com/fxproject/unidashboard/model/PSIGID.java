package com.fxproject.unidashboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PSIGID implements Serializable {

    @Column(name = "person_id")
    private Long professorId;
    @Column(name = "subject_id")
    private Long subjectId;
    @Column(name = "group_id")
    private Long groupId;


    public PSIGID(Long professorId, Long subjectId, Long groupId) {
        this.professorId = professorId;
        this.subjectId = subjectId;
        this.groupId = groupId;
    }

    protected PSIGID() {}

    public Long getProfessorId() {
        return professorId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public Long getGroupId() {
        return groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PSIGID psigid = (PSIGID) o;
        return professorId.equals(psigid.professorId) && subjectId.equals(psigid.subjectId) && groupId.equals(psigid.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professorId, subjectId, groupId);
    }
}
