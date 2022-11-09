package com.fxproject.unidashboard.service;

import com.fxproject.unidashboard.dto.ProfessorDto;
import com.fxproject.unidashboard.email.EmailAddressGenerator;
import com.fxproject.unidashboard.model.Professor;
import com.fxproject.unidashboard.model.Role;
import com.fxproject.unidashboard.model.UniversityAccount;
import com.fxproject.unidashboard.repository.ProfessorRepository;
import com.fxproject.unidashboard.repository.UniversityAccountRepository;
import com.fxproject.unidashboard.repository.impl.ProfessorRepositoryImpl;
import com.fxproject.unidashboard.repository.impl.UniversityAccountRepositoryImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static com.fxproject.unidashboard.email.EmailAddressGenerator.generateUniversityEmailForProfessor;
import static com.fxproject.unidashboard.mapper.ProfessorMapper.mapToProfessor;

public class ProfessorService {

    private final ProfessorRepository professorRepository = new ProfessorRepositoryImpl();
    private final UniversityAccountRepository accountRepository = new UniversityAccountRepositoryImpl();

    public void addProfessor(ProfessorDto professorDto) {
        Professor professor = mapToProfessor(professorDto);
        professorRepository.save(professor);
        professor.setUniversityAccount(createUniversityAccountForProfessor(professor));
        professorRepository.save(professor);
    }

    private UniversityAccount createUniversityAccountForProfessor(Professor professor) {
        UniversityAccount account = new UniversityAccount();
        account.setMember(professor);
        account.setUniversityEmail(generateUniversityEmailForProfessor(professor));
        account.setPassword(UUID.randomUUID().toString()); // this password will be snet on email then must be changed
        account.setRole(Role.ROLE_PROFESSOR);
        account.setCreatedAt(LocalDateTime.now());
        account.setEnabled(false); // default false, after confirm email will be true
        accountRepository.save(account);
        return account;
    }


}
