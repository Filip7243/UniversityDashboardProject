package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.AcademicTitle;
import com.fxproject.unidashboard.model.Professor;
import com.fxproject.unidashboard.model.Subject;
import com.fxproject.unidashboard.model.Year;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends DefaultRepository<Professor, Long>{
    List<Professor> findAllWithAcademicTitle(AcademicTitle title);
    List<Subject> findProfessorSubjects(Long id);
    List<Year> findProfessorYears(Long id);
    Optional<AcademicTitle> findProfessorAcademicTitle(Long id);
    Optional<Professor> findProfessorByUniversityEmail(String universityEmail);
    Optional<Professor> findProfessorByFirstNameAndLastName(String firstName, String lastName);
}
