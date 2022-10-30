package com.fxproject.unidashboard.service;

import com.fxproject.unidashboard.dto.StudentDto;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.repository.impl.StudentRepositoryImpl;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.fxproject.unidashboard.mapper.StudentMapper.mapToStudentDtos;

public class StudentService {

    private StudentRepository studentRepository;
    private EntityManager em;

    public StudentService(EntityManager em) {
        this.em = HibernateUtils.getEntityManager();
        this.studentRepository = new StudentRepositoryImpl(em);
    }

    public List<StudentDto> getAllStudents() {
        try {
            em.getTransaction().begin();
            List<Student> allStudents = studentRepository.getAllStudents();
            em.getTransaction().commit();
            return mapToStudentDtos(allStudents);
        } catch (Exception e) {
            em.getTransaction().rollback();
            return List.of(); // returns empty list
        }
    }

    public StudentDto getStudentWithId(Long id) {
        try {
            em.getTransaction().begin();
            Student student = studentRepository.findStudentWithId(id).orElseThrow();// todo; custom exception
            em.getTransaction().commit();
            return new StudentDto(student.getAlbumId(), student.getFirstName(), student.getSecondName(), student.getLastName(), student.getEmail(),
                    student.getUniversityEmail(), student.getPhoneNumber(), student.getAlbumId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }


}
