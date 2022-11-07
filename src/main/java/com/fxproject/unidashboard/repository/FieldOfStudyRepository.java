package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.FieldOfStudy;
import com.fxproject.unidashboard.model.TypeOfStudy;
import com.fxproject.unidashboard.model.UniversityDepartment;

import java.util.List;
import java.util.Optional;

public interface FieldOfStudyRepository extends DefaultRepository<FieldOfStudy, Long> {

    Optional<FieldOfStudy> findFieldOfStudyByName(String name);
    List<FieldOfStudy> findFieldOfStudyByType(TypeOfStudy type);
    Optional<UniversityDepartment> findFieldOfStudyDepartment(Long id);
    List<FieldOfStudy> findFieldsOfStudyByDepartment(UniversityDepartment department); //todo: maybe just name
}
