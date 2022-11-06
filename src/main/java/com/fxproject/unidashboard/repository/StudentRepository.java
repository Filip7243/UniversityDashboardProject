package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.model.Year;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends DefaultRepository<Student, Long> {

    Optional<Student> findStudentWithAlbumId(String albumId);
    List<Year> findStudentYears(Long id);

}
