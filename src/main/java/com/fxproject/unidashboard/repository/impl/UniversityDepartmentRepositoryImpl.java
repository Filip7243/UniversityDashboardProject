package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.Subject;
import com.fxproject.unidashboard.model.UniversityDepartment;
import com.fxproject.unidashboard.repository.UniversityDepartmentRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class UniversityDepartmentRepositoryImpl implements UniversityDepartmentRepository {

    private final EntityManager em = HibernateUtils.getEntityManager();
    private static final String DEFAULT_QUERY = "SELECT u FROM UniversityDepartment u";

    @Override
    public void save(UniversityDepartment record) {
        var transaction = em.getTransaction(); //todo:

        try {
            transaction.begin();
            if (record.getId() == null) {
                em.persist(record);
            } else {
                em.merge(record);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void removeWithId(Long id) {
        var transaction = em.getTransaction();

        try {
            transaction.begin();
            UniversityDepartment department = findWithId(id).orElseThrow();//todo: exception
            em.remove(department);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Optional<UniversityDepartment> findWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityDepartment> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE u.id = :id", UniversityDepartment.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<UniversityDepartment> findAll() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityDepartment> query = em.createQuery(DEFAULT_QUERY, UniversityDepartment.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public Optional<UniversityDepartment> findDepartmentWithName(String name) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityDepartment> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE u.name =:name", UniversityDepartment.class);
            query.setParameter("name", name);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }
}
