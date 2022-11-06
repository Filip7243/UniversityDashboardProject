package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.AcademicTitle;
import com.fxproject.unidashboard.model.Professor;
import com.fxproject.unidashboard.model.UniversityMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends DefaultRepository<Professor, Long>{
    List<Professor> findAllWithAcademicTitle(AcademicTitle title);
    List<Professor> findProfessorSubjects(Long id);
    List<Professor> findProfessorYears(Long id);
    List<Professor> findProfessorAcademicTitle(Long id);
}
