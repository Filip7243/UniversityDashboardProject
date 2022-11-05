package com.fxproject.unidashboard;

import com.fxproject.unidashboard.dto.StudentDto;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.service.StudentService;
import com.fxproject.unidashboard.utils.HibernateUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HelloController {
    @FXML
    private TextField idInput;
    @FXML
    private ListView<StudentDto> studentsDtoList;
    @FXML
    private Button findButton;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField secondNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private Button addButton;


    private StudentService studentService;
    private ObservableList<StudentDto> students;

    public void initialize() {
        this.studentService = new StudentService(HibernateUtils.getEntityManager());
        this.students = FXCollections.observableList(studentService.getAllStudents());

        // input
        findButton.setDisable(true);
        idInput.textProperty()
                .addListener((obs, oldText, newText) -> findButton.setDisable(obs.getValue().trim().isEmpty()));

    }

    @FXML
    protected void onHelloButtonClick() {
        studentsDtoList.setItems(students);
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

    @FXML
    protected void onAddButtonClick() {

        students = FXCollections.observableList(studentService.getAllStudents());
        studentsDtoList.setItems(students);
    }

    @FXML
    protected void onRemoveButtonClick() {
        Long id = studentsDtoList.getSelectionModel().getSelectedItem().getId();
        studentService.removeStudentWithId(id);
        students = FXCollections.observableList(studentService.getAllStudents());
        studentsDtoList.setItems(students);
    }


}