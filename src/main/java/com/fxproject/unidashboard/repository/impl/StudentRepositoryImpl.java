package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    private EntityManager em;

    public StudentRepositoryImpl(EntityManager em) {
        this.em = HibernateUtils.getEntityManager();
    }

    @Override
    public void saveStudent(Student student) {

    }

    @Override
    public void removeStudentWithId(Long id) {

    }

    @Override
    public void updateStudentWithId(Long id, Object studentDto) {

    }

    @Override
    public Optional<Student> findStudentWithId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }
}
