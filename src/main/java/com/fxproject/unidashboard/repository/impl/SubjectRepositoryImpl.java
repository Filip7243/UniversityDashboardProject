package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.Professor;
import com.fxproject.unidashboard.model.Subject;
import com.fxproject.unidashboard.repository.SubjectRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;
import java.util.WeakHashMap;

public class SubjectRepositoryImpl implements SubjectRepository {

    private final EntityManager em = HibernateUtils.getEntityManager();
    private static final String DEFAULT_QUERY = "SELECT s FROM Subject s";

    @Override
    public void save(Subject record) {
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
            Subject professor = findWithId(id).orElseThrow();//todo: exception
            em.remove(professor);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Optional<Subject> findWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Subject> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE s.id = :id", Subject.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Subject> findAll() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Subject> query = em.createQuery(DEFAULT_QUERY, Subject.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public Optional<Subject> findByName(String name) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Subject> query = em.createQuery(DEFAULT_QUERY + " WHERE s.name = :name", Subject.class);
            query.setParameter("name", name);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }
}
