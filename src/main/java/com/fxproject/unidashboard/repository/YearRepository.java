package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.FieldOfStudy;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.model.Year;

import java.util.List;
import java.util.Optional;

public interface YearRepository extends DefaultRepository<Year, Long>{

    List<Year> findYearsByName(String name);
    List<Year> findYearsByStudent(Student student);
    List<Year> findYearByFieldOfStudy(FieldOfStudy fieldOfStudy);
    Optional<Year> findYearByYearOfStudy(Integer yearOfStudy);
}
