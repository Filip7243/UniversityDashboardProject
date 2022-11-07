package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.UniversityDepartment;

import java.util.Optional;

public interface UniversityDepartmentRepository extends DefaultRepository<UniversityDepartment, Long> {

    Optional<UniversityDepartment> findDepartmentWithName(String name);
}
