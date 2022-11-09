package com.fxproject.unidashboard.service;

import com.fxproject.unidashboard.dto.ProfessorDto;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.ProfessorRepository;
import com.fxproject.unidashboard.repository.SubjectRepository;
import com.fxproject.unidashboard.repository.UniversityAccountRepository;
import com.fxproject.unidashboard.repository.YearRepository;
import com.fxproject.unidashboard.repository.impl.ProfessorRepositoryImpl;
import com.fxproject.unidashboard.repository.impl.SubjectRepositoryImpl;
import com.fxproject.unidashboard.repository.impl.UniversityAccountRepositoryImpl;
import com.fxproject.unidashboard.repository.impl.YearRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.fxproject.unidashboard.email.EmailAddressGenerator.generateUniversityEmailForProfessor;
import static com.fxproject.unidashboard.mapper.ProfessorMapper.*;

public class ProfessorService {

    private final ProfessorRepository professorRepository = new ProfessorRepositoryImpl();
    private final UniversityAccountRepository accountRepository = new UniversityAccountRepositoryImpl();
    private final YearRepository yearRepository = new YearRepositoryImpl();
    private final SubjectRepository subjectRepository = new SubjectRepositoryImpl();

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

    public void removeProfessor(Long id) {
        professorRepository.removeWithId(id);
    }

    public ProfessorDto findProfessorWithId(Long id) {
        Professor professor = professorRepository.findWithId(id).orElseThrow();
        return mapToProfessorDto(professor);
    }

    public List<ProfessorDto> findAllProfessors() {
        List<Professor> all = professorRepository.findAll();
        return mapToProfessorDtos(all);
    }

    public List<ProfessorDto> findProfessorsWithAcademicTitle(AcademicTitle title) {
        List<Professor> all = professorRepository.findAllWithAcademicTitle(title);
        return mapToProfessorDtos(all);
    }

    public List<Subject> findProfessorSubjects(Long id) {
        return professorRepository.findProfessorSubjects(id);
    }

    public List<Year> findProfessorYears(Long id) {
        return professorRepository.findProfessorYears(id);
    }

    public AcademicTitle findProfessorAcademicTitle(Long id) {
        return professorRepository.findProfessorAcademicTitle(id).orElseThrow(); // todo: custom exception
    }

    public ProfessorDto findProfessorByUniversityEmail(String universityEmail) {
        Professor professor = professorRepository.findProfessorByUniversityEmail(universityEmail).orElseThrow();
        return mapToProfessorDto(professor);
    }

    public List<ProfessorDto> findProfessorsByFirstNameAndLastName(String firstName, String lastName) {
        List<Professor> all = professorRepository.findProfessorByFirstNameAndLastName(firstName, lastName);
        return mapToProfessorDtos(all);
    }

    public void addYearsToProfessor(Long professorId, Long yearId) {
        Professor professor = professorRepository.findWithId(professorId).orElseThrow();
        Year year = yearRepository.findWithId(yearId).orElseThrow();

        professor.getYears().add(year);
        professorRepository.save(professor);
    }

    public void addSubjectsToProfessor(Long professorId, String subjectName) {
        Professor professor = professorRepository.findWithId(professorId).orElseThrow();
        Subject subject = subjectRepository.findByName(subjectName).orElseThrow();

        professor.getSubjects().add(subject);
        professorRepository.save(professor);
    }

}
