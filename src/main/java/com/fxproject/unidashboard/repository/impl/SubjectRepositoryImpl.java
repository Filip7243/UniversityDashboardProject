package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.Subject;
import com.fxproject.unidashboard.model.Year;
import com.fxproject.unidashboard.repository.SubjectRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.*;

public class SubjectRepositoryImpl implements SubjectRepository {

    private final EntityManager em = HibernateUtils.getEntityManager();
    private static final String DEFAULT_QUERY = "SELECT s FROM Subject s";


    @Override
    public void removeWithId(Long id) {
        var transaction = em.getTransaction();
        Subject subject = findWithId(id).orElseThrow();//todo: exception
        deleteAllSubjectYears(id);

        try {
            transaction.begin();
            em.remove(subject);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    private void deleteAllSubjectYears(Long id) {
        List<Year> allSubjectYears = findAllSubjectsYears(id);

        allSubjectYears.forEach(year -> {
            Set<Subject> subjects = year.getSubjects();
            subjects.removeIf(subject -> Objects.equals(subject.getId(), id));
        });
    }

    private List<Year> findAllSubjectsYears(Long id) { //todo : maybe public if necesery
        var transaction = em.getTransaction();

        try {
            transaction.begin();
            TypedQuery<Year> query =
                    em.createQuery("SELECT y FROM Year y JOIN y.subjects s WHERE s.id = :id", Year.class);
            query.setParameter("id", id);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return List.of();
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
