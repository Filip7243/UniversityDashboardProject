package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Subject;

import java.util.Optional;

public interface SubjectRepository extends DefaultRepository<Subject, Long>{

    Optional<Subject> findByName(String name);
}
