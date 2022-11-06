package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Professor;
import com.fxproject.unidashboard.model.UniversityMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ProfessorRepository implements DefaultRepository<Professor, Long>{

    private EntityManager em;

    public ProfessorRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Professor record) {
        if(record.getId() == null) {
            em.persist(record);
        } else {
            em.merge(record);
        }
    }

    public Optional<Professor> findWithId(Long id) {
        TypedQuery<Professor> query =
                em.createQuery("SELECT u FROM Professor u WHERE u.id = :id", Professor.class);
        query.setParameter("id", id);
        return Optional.of(query.getSingleResult());
    }

    public void removeWithId(Long id) {
        Professor foundMember = findWithId(id).orElseThrow();//todo: custom exception
        em.remove(foundMember);
    }

    public List<Professor> findAll() {
        return em.createQuery("SELECT u FROM Professor u", Professor.class).getResultList();
    }
}
