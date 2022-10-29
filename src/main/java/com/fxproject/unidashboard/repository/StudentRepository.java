package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    void saveStudent(Student student);
    void removeStudentWithId(Long id);
    Optional<Student> findStudentWithId(Long id);
    List<Student> getAllStudents();

}
