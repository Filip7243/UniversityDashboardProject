package com.fxproject.unidashboard.service;

import com.fxproject.unidashboard.dto.StudentDto;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;

import java.util.List;

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
            return new StudentDto(student.getId(), student.getAlbumId(), student.getFirstName(), student.getSecondName(), student.getLastName(), student.getEmail(),
                    student.getEmail(), student.getPhoneNumber(), student.getAlbumId());//todo:universityEmail
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public void saveStudent(Student student) {
        try {
            em.getTransaction().begin();
            studentRepository.saveStudent(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void removeStudentWithId(Long id) {
        try {
            em.getTransaction().begin();
            studentRepository.removeStudentWithId(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }


}
