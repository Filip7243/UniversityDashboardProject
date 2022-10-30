package com.fxproject.unidashboard;

import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.repository.impl.StudentRepositoryImpl;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.util.UUID;

public class HelloController {
    @FXML
    private Label welcomeText;

    private StudentRepository studentRepo;
    private EntityManager em;

    public void initialize() {
        this.em = HibernateUtils.getEntityManager();
        this.studentRepo = new StudentRepositoryImpl(em);
    }

    @FXML
    protected void onHelloButtonClick() {
        em.getTransaction().begin();
        studentRepo.saveStudent(
                new Student(null, UUID.randomUUID().toString(), "Filip", "Carl", "Doe",
                        "filip@mail.com", "fk122941@stud.ur.edu.pl",
                        LocalDateTime.now(), "Cracow", "1231231231",
                        "32178352813", LocalDateTime.now(), LocalDateTime.now(), true, 1L)
        );
        em.getTransaction().commit();
    }
}