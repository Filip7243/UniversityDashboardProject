package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    private EntityManager em;

    public StudentRepositoryImpl(EntityManager em) {
        this.em = HibernateUtils.getEntityManager();
    }

    @Override
    public void saveStudent(Student student) {
        if(student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
    }

    @Override
    public Optional<Student> findStudentWithId(Long id) {
        TypedQuery<Student> query =
                em.createQuery("SELECT u FROM Student u WHERE u.id = :id", Student.class);
        query.setParameter("id", id);
        return Optional.of(query.getSingleResult());
    }

    @Override
    public void removeStudentWithId(Long id) {
        Student student = findStudentWithId(id).orElseThrow();// todo; custom exception
        em.remove(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return em.createQuery("SELECT u FROM Student u", Student.class).getResultList();
    }
}
