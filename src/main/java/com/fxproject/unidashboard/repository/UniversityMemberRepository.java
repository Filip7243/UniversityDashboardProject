package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.UniversityMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class UniversityMemberRepository implements DefaultRepository<UniversityMember, Long> {

    private EntityManager em;

    public UniversityMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(UniversityMember record) {
        if(record.getId() == null) {
            em.persist(record);
        } else {
            em.merge(record);
        }
    }

    @Override
    public Optional<UniversityMember> findWithId(Long id) {
        TypedQuery<UniversityMember> query =
                em.createQuery("SELECT u FROM UniversityMember u WHERE u.id = :id", UniversityMember.class);
        query.setParameter("id", id);
        return Optional.of(query.getSingleResult());
    }

    @Override
    public void removeWithId(Long id) {
        UniversityMember foundMember = findWithId(id).orElseThrow();//todo: custom exception
        em.remove(foundMember);
    }

    @Override
    public List<UniversityMember> findAll() {
        return em.createQuery("SELECT u FROM UniversityMember u", UniversityMember.class).getResultList();
    }
}
