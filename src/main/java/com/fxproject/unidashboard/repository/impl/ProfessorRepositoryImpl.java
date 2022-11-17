package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.dto.ProfessorDto;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.ProfessorRepository;
import com.fxproject.unidashboard.repository.SubjectRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ProfessorRepositoryImpl implements ProfessorRepository {

    private final EntityManager em = HibernateUtils.getEntityManager();
    private final SubjectRepository subjectRepository = new SubjectRepositoryImpl();
    private static final String DEFAULT_QUERY = "SELECT p FROM Professor p";

    @Override
    public void removeWithId(Long id) {
        var transaction = em.getTransaction();
        Professor professor = findWithId(id).orElseThrow();//todo: exception
        try {
            deleteAllProfessorSubject(id);
            transaction.begin();
            em.remove(professor);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    private void deleteAllProfessorSubject(Long id) {
        List<Subject> allProfessorSubjects = findProfessorSubjects(id);

        allProfessorSubjects.forEach(subject -> {
            Set<Professor> professors = subject.getProfessors();
            professors.removeIf(professor -> Objects.equals(professor.getId(), id));
        });
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
            TypedQuery<Subject> query =
                    em.createQuery("SELECT s FROM Subject s JOIN s.professors p WHERE p.id = :id", Subject.class);
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
            TypedQuery<Year> query =
                    em.createQuery("SELECT y FROM Year y JOIN y.subjects s JOIN s.professors p WHERE p.id = :id", Year.class);
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
                    em.createQuery(DEFAULT_QUERY + " WHERE p.universityAccount.universityEmail = :universityEmail", Professor.class);
            query.setParameter("universityEmail", universityEmail);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            System.out.println("MSG = " + e.getMessage());
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Professor> findProfessorByFirstNameAndLastName(String firstName, String lastName) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Professor> query =
                    em.createQuery("SELECT p FROM Professor p WHERE p.firstName = :firstName AND p.lastName = :lastName", Professor.class);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }
}
