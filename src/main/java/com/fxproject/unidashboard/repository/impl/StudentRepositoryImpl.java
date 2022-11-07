package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.model.Year;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    private EntityManager em = HibernateUtils.getEntityManager();
    private static final String DEFAULT_QUERY = "SELECT s FROM Student s";

    @Override
    public void save(Student record) {
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
        Student student = findWithId(id).orElseThrow();// todo: custom exception

        var transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(student);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Optional<Student> findWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Student> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE s.id = :id", Student.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findAll() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Student> query = em.createQuery(DEFAULT_QUERY, Student.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public Optional<Student> findStudentByAlbumId(String albumId) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Student> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE s.albumId = :albumId", Student.class);
            query.setParameter("albumId", albumId);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Year> findStudentYears(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Year> query = em.createQuery("SELECT s.years FROM Student s WHERE s.id = :id", Year.class);
            query.setParameter("id", id);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public Optional<Student> findStudentByUniversityEmail(String universityEmail) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Student> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE s.universityAccount.universityEmail = :universityEmail", Student.class);
            query.setParameter("universityEmail", universityEmail);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findStudentByFirstNameAndLastName(String firstName, String lastName) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Student> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE s.firstName = :firstName AND s.lastName = :lastName", Student.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }
}
