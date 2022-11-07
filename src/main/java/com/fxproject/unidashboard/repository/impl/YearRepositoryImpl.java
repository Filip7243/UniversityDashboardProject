package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.FieldOfStudy;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.model.Wage;
import com.fxproject.unidashboard.model.Year;
import com.fxproject.unidashboard.repository.YearRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class YearRepositoryImpl implements YearRepository {

    private EntityManager em = HibernateUtils.getEntityManager();
    public static final String DEFAULT_QUERY = "SELECT y FROM Year y";

    @Override
    public void save(Year record) {
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
        Year year = findWithId(id).orElseThrow();// todo: custom exception

        var transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(year);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Optional<Year> findWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Year> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE y.id = :id", Year.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Year> findAll() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Year> query = em.createQuery(DEFAULT_QUERY, Year.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public List<Year> findYearsByName(String name) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Year> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE y.name = :name", Year.class);
            query.setParameter("name", name);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public List<Year> findYearsByStudent(Student student) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Year> query = em.createQuery(DEFAULT_QUERY + " JOIN Student s WHERE s = :student", Year.class);
            query.setParameter("student", student);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public List<Year> findYearByFieldOfStudy(FieldOfStudy fieldOfStudy) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Year> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE y.fieldOfStudy = :fieldOfStudy", Year.class);
            query.setParameter("fieldOfStudy", fieldOfStudy);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public Optional<Year> findYearByYearOfStudy(Integer yearOfStudy) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Year> query = em.createQuery(DEFAULT_QUERY + " WHERE y.yearOfStudy = :yearOfStudy", Year.class);
            query.setParameter("yearOfStudy", yearOfStudy);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }
}
