package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.MarkRepository;
import com.fxproject.unidashboard.repository.ProfessorsSubjectsInGroupsRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GradeStudentController {

    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;
    @FXML
    private ComboBox<Groups> comboGroups;
    @FXML
    private ComboBox<Subjects> comboSubjects;
    @FXML
    private ComboBox<Students> comboStudents;
    @FXML
    private Spinner<Double> markSpinner;
    @FXML
    private TextField description;
    @FXML
    private ComboBox<ExamTypes> comboTypes;
    private StudentRepository sr = new StudentRepository();
    private ProfessorsSubjectsInGroupsRepository psigr = new ProfessorsSubjectsInGroupsRepository();
    private MarkRepository mr = new MarkRepository();

    public void initialize() {
        comboSubjects.setVisible(false);
        comboStudents.setVisible(false);
        cancelButton.setVisible(false);
        submitButton.setVisible(false);
        markSpinner.setVisible(false);
        description.setVisible(false);
        comboTypes.setVisible(false);

        markSpinner.setPromptText("Mark");
        markSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(2.0, 5.0, 2.0, 0.5));

        Professors professor = (Professors) UserSession.getSession().getPerson();
        Set<Groups> groups = professor.getPsig().stream().map(ProfessorsSubjectsInGroups::getGroup).collect(Collectors.toSet());
        comboGroups.setItems(FXCollections.observableArrayList(groups));

        comboGroups.valueProperty().addListener((obs, oldValue, newValue) -> {
            comboStudents.setItems(FXCollections.observableArrayList(sr.getStudentsFromGroup(newValue)));
            comboSubjects.setItems(FXCollections.observableArrayList(psigr.findProfessorSubjectInGroup(newValue, professor)));
            comboTypes.getItems().addAll(ExamTypes.values());
            comboSubjects.setVisible(true);
            comboStudents.setVisible(true);
            cancelButton.setVisible(true);
            submitButton.setVisible(true);
            markSpinner.setVisible(true);
            description.setVisible(true);
            comboTypes.setVisible(true);
        });
    }

    public void cancelGrade() {
        System.out.println("CANCEL");
    }

    public void submitGrade() {
        Marks mark = new Marks(null, markSpinner.getValue(), LocalDateTime.now(), description.getText(), comboStudents.getValue(), comboSubjects.getValue(), comboTypes.getValue());
        mr.save(mark);
    }
}
