package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.UniversityMember;

import java.util.List;
import java.util.Optional;

public interface UniversityMemberRepository extends DefaultRepository<UniversityMember, Long> {

    Optional<UniversityMember> findByPesel(String pesel);
    Optional<UniversityMember> findByEmail(String email);
    Optional<UniversityMember> findByUniversityEmail(String universityEmail);
    List<UniversityMember> findByFirstNameAndLastName(String firstName, String lastName);
}
