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

        var transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(fieldOfStudy);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Optional<FieldOfStudy> findWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<FieldOfStudy> query =
                    em.createQuery("SELECT f FROM FieldOfStudy f WHERE f.id = :id", FieldOfStudy.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<FieldOfStudy> findAll() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            List<FieldOfStudy> allFieldsOfStudy =
                    em.createQuery("SELECT f FROM FieldOfStudy f", FieldOfStudy.class).getResultList();
            transaction.commit();
            return allFieldsOfStudy;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return List.of();
        }
    }

    @Override
    public Optional<FieldOfStudy> findFieldOfStudyByName(String name) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<FieldOfStudy> query =
                    em.createQuery("SELECT f FROM FieldOfStudy f WHERE f.name = :name", FieldOfStudy.class);
            query.setParameter("name", name);
            transaction.commit();
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<FieldOfStudy> findFieldOfStudyByType(TypeOfStudy type) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<FieldOfStudy> query =
                    em.createQuery("SELECT f FROM FieldOfStudy f WHERE f.type = :type", FieldOfStudy.class);
            query.setParameter("type", type);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }

    }

    @Override
    public Optional<UniversityDepartment> findFieldOfStudyDepartment(Long id) {
        var transaction = em.getTransaction();

        try {
            transaction.begin();
            TypedQuery<UniversityDepartment> query =
                    em.createQuery("SELECT f.department FROM FieldOfStudy f WHERE f.id = :id", UniversityDepartment.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<FieldOfStudy> findFieldsOfStudyByDepartment(UniversityDepartment department) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<FieldOfStudy> query =
                    em.createQuery("SELECT f FROM FieldOfStudy f WHERE f.department = :department", FieldOfStudy.class);
            query.setParameter("department", department);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return List.of();
        }
    }

}
