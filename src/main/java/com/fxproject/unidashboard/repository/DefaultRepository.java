package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.UniversityDepartment;
import com.fxproject.unidashboard.model.UniversityMember;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public interface DefaultRepository<E, T> { // E - model T - id type

    void save(E record);
    void removeWithId(T id);
    Optional<E> findWithId(T id);
    List<E> findAll();

}
