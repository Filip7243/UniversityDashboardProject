package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.FieldOfStudy;
import com.fxproject.unidashboard.model.TypeOfStudy;
import com.fxproject.unidashboard.model.UniversityDepartment;
import com.fxproject.unidashboard.repository.FieldOfStudyRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class FieldOfStudyRepositoryImpl implements FieldOfStudyRepository {

    private final EntityManager em = HibernateUtils.getEntityManager();

    @Override
    public void removeWithId(Long id) {
        FieldOfStudy fieldOfStudy = findWithId(id).orElseThrow();//todo: custom exception
        em.getTransaction().begin();
        em.remove(fieldOfStudy);
        em.getTransaction().commit();
    }

    @Override
    public Optional<FieldOfStudy> findWithId(Long id) {
        em.getTransaction().begin();
        TypedQuery<FieldOfStudy> query =
                em.createQuery("SELECT f FROM FieldOfStudy f WHERE f.id = :id", FieldOfStudy.class);
        query.setParameter("id", id);
        em.getTransaction().commit();
        return Optional.of(query.getSingleResult());
    }

    @Override
    public List<FieldOfStudy> findAll() {
        em.getTransaction().begin();
        List<FieldOfStudy> allFieldsOfStudy =
                em.createQuery("SELECT f FROM FieldOfStudy f", FieldOfStudy.class).getResultList();
        em.getTransaction().commit();
        return allFieldsOfStudy;
    }

    @Override
    public Optional<FieldOfStudy> findFieldOfStudyByName(String name) {
        em.getTransaction().begin();
        TypedQuery<FieldOfStudy> query =
                em.createQuery("SELECT f FROM FieldOfStudy f WHERE f.name = :name", FieldOfStudy.class);
        query.setParameter("name", name);
        em.getTransaction().commit();
        return Optional.of(query.getSingleResult());
    }

    @Override
    public List<FieldOfStudy> findFieldOfStudyByType(TypeOfStudy type) {
        em.getTransaction().begin();
        TypedQuery<FieldOfStudy> query =
                em.createQuery("SELECT f FROM FieldOfStudy f WHERE f.type = :type", FieldOfStudy.class);
        query.setParameter("type", type);
        em.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public Optional<UniversityDepartment> findFieldOfStudyDepartment(Long id) {
        em.getTransaction().begin();
        TypedQuery<UniversityDepartment> query =
                em.createQuery("SELECT f.department FROM FieldOfStudy f WHERE f.id = :id", UniversityDepartment.class);
        query.setParameter("id", id);
        em.getTransaction().commit();
        return Optional.of(query.getSingleResult());
    }

    @Override
    public List<FieldOfStudy> findFieldsOfStudyByDepartment(UniversityDepartment department) {
        em.getTransaction().begin();
        TypedQuery<FieldOfStudy> query =
                em.createQuery("SELECT f FROM FieldOfStudy f WHERE f.department = :department", FieldOfStudy.class);
        query.setParameter("department", department);
        em.getTransaction().commit();
        return query.getResultList();
    }
}
