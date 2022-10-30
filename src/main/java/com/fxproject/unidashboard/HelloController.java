package com.fxproject.unidashboard;

import com.fxproject.unidashboard.dto.StudentDto;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.repository.impl.StudentRepositoryImpl;
import com.fxproject.unidashboard.service.StudentService;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ListView<StudentDto> studentsDtoList;

    private StudentService studentService;
    private List<StudentDto> students;

    public void initialize() {
        this.studentService = new StudentService(HibernateUtils.getEntityManager());
        this.students = FXCollections.observableList(studentService.getAllStudents());
    }

    @FXML
    protected void onHelloButtonClick() {
        studentsDtoList.setItems((ObservableList<StudentDto>) students);
    }
}