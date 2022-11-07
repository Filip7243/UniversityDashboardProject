package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.Wage;
import com.fxproject.unidashboard.repository.WageRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class WageRepositoryImpl implements WageRepository {

    private EntityManager em = HibernateUtils.getEntityManager();
    private static final String DEFAULT_QUERY = "SELECT w FROM Wage w";

    @Override
    public void save(Wage record) {
        var transaction = em.getTransaction();
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
        Wage wage = findWithId(id).orElseThrow();// todo: custom exception

        var transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(wage);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Optional<Wage> findWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Wage> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE w.id = :id", Wage.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Wage> findAll() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Wage> query = em.createQuery(DEFAULT_QUERY, Wage.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public List<Wage> findWagesByPayday(LocalDateTime payday) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Wage> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE w.payday = :payday", Wage.class);
            query.setParameter("payday", payday);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public List<Wage> findWagesByEmployeePesel(String pesel) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Wage> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE w.employee.pesel = :pesel", Wage.class);
            query.setParameter("pesel", pesel);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }
}
