package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.dto.GradeStudentDto;
import com.fxproject.unidashboard.model.Grade;
import com.fxproject.unidashboard.model.Professor;
import com.fxproject.unidashboard.model.Student;

import java.util.List;

public interface GradeRepository extends DefaultRepository<Grade, Long>{

    List<Grade> findStudentGrades(Student student);
    List<Grade> findProfessorGrades(Professor professor);
    void gradeStudent(GradeStudentDto grade);
}
