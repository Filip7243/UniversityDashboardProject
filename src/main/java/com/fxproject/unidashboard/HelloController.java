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
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HelloController {
    @FXML
    private TextField idInput;
    @FXML
    private ListView<StudentDto> studentsDtoList;
    @FXML
    private Button findButton;

    private StudentService studentService;
    private List<StudentDto> students;

    public void initialize() {
        this.studentService = new StudentService(HibernateUtils.getEntityManager());
        this.students = FXCollections.observableList(studentService.getAllStudents());

        // input for finding
        findButton.setDisable(true);
        idInput.textProperty().addListener((obs, oldText, newText) -> {
            findButton.setDisable(obs.getValue().trim().isEmpty());
        });
    }

    @FXML
    protected void onHelloButtonClick() {
        studentsDtoList.setItems((ObservableList<StudentDto>) students);
    }

    @FXML
    protected void onFindButtonClick() {
        Long id = Long.parseLong(idInput.getText());
        StudentDto studentWithId = studentService.getStudentWithId(id);
        ArrayList<StudentDto> list = new ArrayList<>();
        ObservableList<StudentDto> s = FXCollections.observableList(list);
        s.add(studentWithId);

        studentsDtoList.setItems(s);
    }
}