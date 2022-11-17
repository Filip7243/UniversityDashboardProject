package com.fxproject.unidashboard.service;

import com.fxproject.unidashboard.dto.StudentDto;
import com.fxproject.unidashboard.dto.YearDto;
import com.fxproject.unidashboard.email.EmailAddressGenerator;
import com.fxproject.unidashboard.model.Role;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.model.UniversityAccount;
import com.fxproject.unidashboard.model.Year;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.repository.YearRepository;
import com.fxproject.unidashboard.repository.impl.StudentRepositoryImpl;
import com.fxproject.unidashboard.repository.impl.YearRepositoryImpl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.fxproject.unidashboard.mapper.StudentMapper.mapToStudentDto;
import static com.fxproject.unidashboard.mapper.StudentMapper.mapToStudentDtos;
import static com.fxproject.unidashboard.mapper.YearMapper.mapToYearDtos;

public class StudentService {

    private final StudentRepository studentRepository = new StudentRepositoryImpl();
    private final YearRepository yearRepository = new YearRepositoryImpl();

    public void addStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setAlbumId(studentDto.getAlbumId());
        student.setFirstName(studentDto.getFirstName());
        student.setSecondName(studentDto.getSecondName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setPlaceOfBirth(studentDto.getPlaceOfBirth());
        student.setPesel(studentDto.getPesel());
        student.setYears(new HashSet<>());
        student.setUniversityAccount(createUniversityAccountForStudent(student));
        studentRepository.save(student);
    }

    private UniversityAccount createUniversityAccountForStudent(Student student) {
        UniversityAccount account = new UniversityAccount();
        account.setUniversityEmail(EmailAddressGenerator.generateUniversityEmailForStudent(student));
        account.setPassword(UUID.randomUUID().toString());
        account.setRole(Role.ROLE_STUDENT);
        account.setCreatedAt(LocalDateTime.now());
        account.setEnabled(false);

        return account;
    }

    public void removeStudentWithId(Long id) {
        studentRepository.removeWithId(id);
    }

    public StudentDto findStudentWithId(Long id) {
        Student student = studentRepository.findWithId(id).orElseThrow();
        return mapToStudentDto(student);
    }

    public List<StudentDto> findAllStudents() {
        List<Student> all = studentRepository.findAll();
        return mapToStudentDtos(all);
    }
    
    public StudentDto findStudentWithAlbumId(String albumId) {
        Student student = studentRepository.findStudentByAlbumId(albumId).orElseThrow();
        return mapToStudentDto(student);
    }
    
    public List<YearDto> findStudentYears(Long id) {
        List<Year> studentYears = studentRepository.findStudentYears(id);
        return mapToYearDtos(studentYears);
    }
    
    public StudentDto findStudentByUniversityEmail(String universityEmail) {
        Student student = studentRepository.findStudentByUniversityEmail(universityEmail).orElseThrow();
        return mapToStudentDto(student);
    }
    
    public List<StudentDto> findStudentByFirstNameAndLastName(String firstName, String lastName) {
        List<Student> all = studentRepository.findStudentByFirstNameAndLastName(firstName, lastName);
        return mapToStudentDtos(all);
    }

    public void addYearToStudent(Long studentId, Long yearId) {
        Year year = yearRepository.findWithId(yearId).orElseThrow();
        Student student = studentRepository.findWithId(studentId).orElseThrow();

        student.getYears().add(year);

        studentRepository.save(student);
    }
}
