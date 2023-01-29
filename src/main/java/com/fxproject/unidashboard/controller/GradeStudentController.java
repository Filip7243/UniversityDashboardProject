package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.MarkRepository;
import com.fxproject.unidashboard.repository.ProfessorsSubjectsInGroupsRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fxproject.unidashboard.validator.Validator.checkIfEmpty;
import static com.fxproject.unidashboard.validator.Validator.checkIfValueInComboSelected;

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
        List<ProfessorsSubjectsInGroups> professorPSIG = psigr.findProfessorPSIG(professor);
        for (ProfessorsSubjectsInGroups professorsSubjectsInGroups : professorPSIG) {
            System.out.println("ESSUNIA: " + professorsSubjectsInGroups);
        }
        Set<Groups> groups = professorPSIG.stream().map(ProfessorsSubjectsInGroups::getGroup).collect(Collectors.toSet());
        comboGroups.setItems(FXCollections.observableArrayList(groups));
        comboTypes.getItems().addAll(ExamTypes.values());

        comboGroups.valueProperty().addListener((obs, oldValue, newValue) -> {
            comboStudents.setItems(FXCollections.observableArrayList(sr.getStudentsFromGroup(newValue)));
            comboSubjects.setItems(FXCollections.observableArrayList(psigr.findProfessorSubjectInGroup(newValue, professor)));
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
        comboGroups.getSelectionModel().clearSelection();
        comboSubjects.setVisible(false);
        comboStudents.setVisible(false);
        cancelButton.setVisible(false);
        submitButton.setVisible(false);
        markSpinner.setVisible(false);
        description.setVisible(false);
        comboTypes.setVisible(false);
    }

    public void submitGrade() {
        if (!validateTextFields()) {
        } else if (!validateCombos()) {
        } else {
            Marks mark = new Marks(null, markSpinner.getValue(), LocalDate.now(), description.getText(), comboStudents.getValue(), comboSubjects.getValue(), comboTypes.getValue());
            mr.save(mark);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Added!");
            a.show();
            clearFields();
        }
    }

    private boolean validateCombos() {
        return checkIfValueInComboSelected(List.of(comboStudents, comboGroups, comboSubjects, comboTypes));
    }

    private boolean validateTextFields() {
        return checkIfEmpty(List.of(description));
    }

    private void clearFields() {
        comboGroups.getSelectionModel().clearSelection();
        comboSubjects.getSelectionModel().clearSelection();
        comboTypes.getSelectionModel().clearSelection();
        comboStudents.getSelectionModel().clearSelection();
        markSpinner.getValueFactory().setValue(2.0);
        description.clear();
    }
}
