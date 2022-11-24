package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.dto.GradeStudentDto;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.*;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class GradeRepositoryImpl implements GradeRepository {

    private final EntityManager em = HibernateUtils.getEntityManager();
    private final StudentRepository studentRepository = new StudentRepositoryImpl();
    private final SubjectRepository subjectRepository = new SubjectRepositoryImpl();
    private final ProfessorRepository professorRepository = new ProfessorRepositoryImpl();
    private final YearRepository yearRepository = new YearRepositoryImpl();
    private static final String DEFAULT_QUERY = "SELECT g FROM Grade g";

    @Override
    public void removeWithId(Long id) {
        Grade grade = findWithId(id).orElseThrow(); // todo; exception
        var transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(grade);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Grade> findWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Grade> query = em.createQuery(DEFAULT_QUERY + " WHERE g.id = :id", Grade.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Grade> findAll() {
        var transaction = em.getTransaction();

        try {
            transaction.begin();
            TypedQuery<Grade> query = em.createQuery(DEFAULT_QUERY, Grade.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<Grade> findStudentGrades(Student student) {
        var transaction = em.getTransaction();

        try {
            transaction.begin();
            TypedQuery<Grade> query = em.createQuery("SELECT g FROM Grade g WHERE student = :student", Grade.class);
            query.setParameter("student", student);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<Grade> findProfessorGrades(Professor professor) {
        var transaction = em.getTransaction();

        try {
            transaction.begin();
            TypedQuery<Grade> query = em.createQuery("SELECT g FROM Grade g WHERE professor = :professor", Grade.class);
            query.setParameter("professor", professor);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void gradeStudent(GradeStudentDto grade) {
        Professor professor = professorRepository.findWithId(grade.getProfessorId()).orElseThrow();//todo;exception
        Subject subject = subjectRepository.findWithId(grade.getSubjectId()).orElseThrow();
        if (!subject.getProfessors().contains(professor)) {
            System.out.println("Professor can't grade student on this subject because he is not teaching it");
            return;
        }
        Long studentId = grade.getStudentId();
        List<Year> studentYears = studentRepository.findStudentYears(studentId);
        if(studentYears.isEmpty()) {
            return;
        }
        for(Year y : studentYears) {
            if(y.getSubjects().contains(subject)) {
                Student student = studentRepository.findWithId(studentId).orElseThrow();
                Grade newGrade = new Grade();
                newGrade.setDescription(grade.getDescription());
                newGrade.setGrade(grade.getGrade());
                newGrade.setStudent(student);
                newGrade.setProfessor(professor);
                newGrade.setSubject(subject);
                save(newGrade);
                break;
            }
        }
    }
}
