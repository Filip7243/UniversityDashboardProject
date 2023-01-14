package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.ExamRepository;
import com.fxproject.unidashboard.repository.ProfessorsSubjectsInGroupsRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.List;

import static com.fxproject.unidashboard.validator.Validator.*;

public class AddExamController {

    @FXML
    private ComboBox<Groups> comboGroups;

    @FXML
    private ComboBox<Subjects> comboSubjects;

    @FXML
    private ComboBox<ExamTypes> comboTypes;

    @FXML
    private DatePicker examDatePicker;

    @FXML
    private TextField examNameField;

    private ExamRepository er = new ExamRepository();
    private ProfessorsSubjectsInGroupsRepository psigr = new ProfessorsSubjectsInGroupsRepository();

    @FXML
    private void initialize() {
        Professors professor = (Professors) UserSession.getSession().getPerson();
        List<ProfessorsSubjectsInGroups> psigs = professor.getPsig();
        List<Groups> groups = psigs.stream().map(ProfessorsSubjectsInGroups::getGroup).toList();
        comboGroups.setItems(FXCollections.observableArrayList(groups));
        comboGroups.valueProperty().addListener((obs, oldValue, newValue) -> {
            List<Subjects> professorSubjectInGroup = psigr.findProfessorSubjectInGroup(newValue, professor);
            comboSubjects.setItems(FXCollections.observableArrayList(professorSubjectInGroup));
        });
        comboTypes.getItems().addAll(ExamTypes.values());
    }

    public void addExam() {
        if (!validateTextFields()) {
        } else if (!validateComboBoxes()) {
        } else if (!validateDatePicker()) {
        } else {
            Exams exam = new Exams();
            exam.setExamName(examNameField.getText());
            exam.setGroup(comboGroups.getValue());
            exam.setSubject(comboSubjects.getValue());
            exam.setExamType(comboTypes.getValue());
            exam.setExamDate(examDatePicker.getValue().atStartOfDay());
            er.save(exam);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Added!");
            a.show();
            clearFields();
        }
    }

    private boolean validateComboBoxes() {
        return checkIfValueInComboSelected(List.of(comboGroups, comboSubjects, comboTypes));
    }

    private boolean validateTextFields() {
        return checkIfEmpty(List.of(examNameField));
    }

    private boolean validateDatePicker() {
        return checkIfDateWasPicked(examDatePicker);
    }

    public void cancelAdding() {
        clearFields();
    }

    private void clearFields() {
        comboTypes.getSelectionModel().clearSelection();
        comboSubjects.getSelectionModel().clearSelection();
        comboGroups.getSelectionModel().clearSelection();
        examNameField.clear();
        examDatePicker.getEditor().clear();
    }
}
