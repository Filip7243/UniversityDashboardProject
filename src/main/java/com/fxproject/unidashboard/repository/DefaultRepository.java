package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.UniversityDepartment;
import com.fxproject.unidashboard.model.UniversityMember;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public interface DefaultRepository<E, T> { // E - model T - id type

    EntityManager em = HibernateUtils.getEntityManager();

    default void save(E record) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(record);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

    }

    void removeWithId(T id);

    Optional<E> findWithId(T id);

    List<E> findAll();

}
