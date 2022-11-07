package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.AcademicTitle;
import com.fxproject.unidashboard.model.Professor;
import com.fxproject.unidashboard.model.Subject;
import com.fxproject.unidashboard.model.Year;
import com.fxproject.unidashboard.repository.ProfessorRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.security.spec.ECField;
import java.util.List;
import java.util.Optional;

public class ProfessorRepositoryImpl implements ProfessorRepository {

    private final EntityManager em = HibernateUtils.getEntityManager();
    private static final String DEFAULT_QUERY = "SELECT p FROM Professor p";

    @Override
    public void save(Professor record) {
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
        var transaction = em.getTransaction();

        try {
            transaction.begin();
            Professor professor = findWithId(id).orElseThrow();//todo: exception
            em.remove(professor);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Optional<Professor> findWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Professor> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE p.id = :id", Professor.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Professor> findAll() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Professor> query = em.createQuery(DEFAULT_QUERY, Professor.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public List<Professor> findAllWithAcademicTitle(AcademicTitle title) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Professor> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE p.academicTitle = :title", Professor.class);
            query.setParameter("title", title);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public List<Subject> findProfessorSubjects(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Subject> query = em.createQuery("SELECT p.subjects FROM Professor p WHERE p.id = :id", Subject.class);
            query.setParameter("id", id);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public List<Year> findProfessorYears(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Year> query = em.createQuery("SELECT p.years FROM Professor p WHERE p.id = :id", Year.class);
            query.setParameter("id", id);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public Optional<AcademicTitle> findProfessorAcademicTitle(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<AcademicTitle> query =
                    em.createQuery("SELECT p.academicTitle FROM Professor p WHERE p.id = :id", AcademicTitle.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }

    }

    @Override
    public Optional<Professor> findProfessorByUniversityEmail(String universityEmail) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Professor> query =
                    em.createQuery(DEFAULT_QUERY + " JOIN UniversityAccount u ON u.member = p.universityAccount " +
                                   "WHERE u.universityEmail = :universityEmail", Professor.class);
            query.setParameter("universityEmail", universityEmail);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Professor> findProfessorByFirstNameAndLastName(String firstName, String lastName) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Professor> query =
                    em.createQuery("SELECT p FROM Professor p WHERE p.firstName = :firstName AND p.lastName = :lastName", Professor.class);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }
}