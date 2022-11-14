package com.fxproject.unidashboard.service;

import com.fxproject.unidashboard.dto.YearDto;
import com.fxproject.unidashboard.mapper.YearMapper;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.*;
import com.fxproject.unidashboard.repository.impl.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.fxproject.unidashboard.mapper.YearMapper.mapToYearDto;
import static com.fxproject.unidashboard.mapper.YearMapper.mapToYearDtos;
import static java.time.LocalDateTime.now;
import static java.time.Month.*;

public class YearService {

    private final YearRepository yearRepository = new YearRepositoryImpl();
    private final UniversityAccountRepository accountRepository = new UniversityAccountRepositoryImpl();
    private final FieldOfStudyRepository fieldOfStudyRepository = new FieldOfStudyRepositoryImpl();
    private final StudentRepository studentRepository = new StudentRepositoryImpl();
    private final SubjectRepository subjectRepository = new SubjectRepositoryImpl();


    public void removeYearWithId(Long id) {
        yearRepository.removeWithId(id);
    }

    public YearDto findYearWithId(Long id) {
        Year year = yearRepository.findWithId(id).orElseThrow();// todo: custom excpetion
        return mapToYearDto(year);
    }

    public List<YearDto> findAllYears() {
        List<Year> all = yearRepository.findAll();
        return mapToYearDtos(all);
    }

    public List<YearDto> findYearsByName(String name) {
        List<Year> all = yearRepository.findYearsByName(name);
        return mapToYearDtos(all);
    }

    public List<YearDto> findYearsByStudent(String universityEmail) {
        UniversityAccount account = accountRepository.findAccountByUniversityEmail(universityEmail).orElseThrow();// todo: custom excpetion
        Student student = (Student) account.getMember(); //todo: catch exception when trying to cast other model than stuent
        List<Year> all = yearRepository.findYearsByStudent(student);
        return mapToYearDtos(all);
    }

    public List<YearDto> findYearByFieldOfStudy(String fieldOfStudyName) {
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findFieldOfStudyByName(fieldOfStudyName).orElseThrow(); // todo: custom excpetion
        List<Year> all = yearRepository.findYearByFieldOfStudy(fieldOfStudy);
        return mapToYearDtos(all);
    }

    public List<YearDto> findYearsByYearOfStudy(Integer yearOfStudy) {
        List<Year> all = yearRepository.findYearsByYearOfStudy(yearOfStudy);
        return mapToYearDtos(all);
    }

    public void addYear(YearDto yearDto, String fieldOfStudyName) {
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findFieldOfStudyByName(fieldOfStudyName).orElseThrow();
        Year year = new Year();
        year.setName(yearDto.getName());
        year.setStudents(new HashSet<>());
        year.setFieldOfStudy(fieldOfStudy); // todo: field of study should be enum maybe
        year.setYearOfStudy(yearDto.getYearOfStudy());
        year.setStartedStudies(LocalDateTime.of(now().getYear(), OCTOBER, 1, 8, 0));
        year.setSubjects(new HashSet<>());

        yearRepository.save(year);
    }

    public void addStudentToYear(Long yearId, Long studentId) {
        Year year = yearRepository.findWithId(yearId).orElseThrow();// todo: custom excpetion
        Student student = studentRepository.findWithId(studentId).orElseThrow();

        year.getStudents().add(student);
        yearRepository.save(year);

        student.getYears().add(year);
        studentRepository.save(student);
    }

    public void addSubjectToYear(Long yearId, Long subjectId) {
        Year year = yearRepository.findWithId(yearId).orElseThrow();// todo: custom excpetion
        Subject subject = subjectRepository.findWithId(subjectId).orElseThrow();

        year.getSubjects().add(subject);
        yearRepository.save(year);
    }

}
