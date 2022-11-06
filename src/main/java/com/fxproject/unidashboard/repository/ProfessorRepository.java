package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends DefaultRepository<Professor, Long>{
    List<Professor> findAllWithAcademicTitle(AcademicTitle title);
    List<Subject> findProfessorSubjects(Long id);
    List<Year> findProfessorYears(Long id);
    List<AcademicTitle> findProfessorAcademicTitle(Long id);
}
