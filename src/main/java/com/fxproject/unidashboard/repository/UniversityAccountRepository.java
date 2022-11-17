package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.UniversityAccount;
import com.fxproject.unidashboard.model.UniversityMember;

import java.util.List;
import java.util.Optional;

public interface UniversityAccountRepository extends DefaultRepository<UniversityAccount, Long>{

    Optional<UniversityAccount> findAccountByUniversityEmail(String universityEmail);
    List<UniversityAccount> findAllDisabledAccounts();
    List<UniversityAccount> findAllEnableAccounts();
}
