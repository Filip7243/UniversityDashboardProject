package com.fxproject.unidashboard.service;

import com.fxproject.unidashboard.dto.StudentDto;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.repository.impl.StudentRepositoryImpl;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;
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
        em.getTransaction().begin();
        studentRepository.saveStudent(new Student(null, UUID.randomUUID().toString(), "Filip", "Carl", "Doe",
                "filip@mail.com", "fk122941@stud.ur.edu.pl",
                LocalDateTime.now(), "Cracow", "1231231231",
                "32178352813", LocalDateTime.now(), LocalDateTime.now(), true, 1L));
        studentRepository.saveStudent(new Student(null, UUID.randomUUID().toString(), "Zibi", "Carl", "Doe",
                "filip@mail.com", "fk122941@stud.ur.edu.pl",
                LocalDateTime.now(), "Cracow", "1231231231",
                "32178352813", LocalDateTime.now(), LocalDateTime.now(), true, 1L));
        studentRepository.saveStudent(new Student(null, UUID.randomUUID().toString(), "Mark", "Carl", "Doe",
                "filip@mail.com", "fk122941@stud.ur.edu.pl",
                LocalDateTime.now(), "Cracow", "1231231231",
                "32178352813", LocalDateTime.now(), LocalDateTime.now(), true, 1L));
        studentRepository.saveStudent(new Student(null, UUID.randomUUID().toString(), "Essa", "Carl", "Doe",
                "filip@mail.com", "fk122941@stud.ur.edu.pl",
                LocalDateTime.now(), "Cracow", "1231231231",
                "32178352813", LocalDateTime.now(), LocalDateTime.now(), true, 1L));

        List<Student> allStudents = studentRepository.getAllStudents();
        em.getTransaction().commit();
        return mapToStudentDtos(allStudents);
    }


}
