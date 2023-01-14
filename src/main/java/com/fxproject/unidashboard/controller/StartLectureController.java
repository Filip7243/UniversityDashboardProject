package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.LectureRepository;
import com.fxproject.unidashboard.repository.ProfessorsSubjectsInGroupsRepository;
import com.fxproject.unidashboard.utils.UserSession;
import com.fxproject.unidashboard.validator.Validator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fxproject.unidashboard.validator.Validator.checkIfEmpty;
import static com.fxproject.unidashboard.validator.Validator.checkIfValueInComboSelected;

public class StartLectureController {

    @FXML
    private VBox container;
    @FXML
    private ComboBox<Groups> comboGroup;

    @FXML
    private ComboBox<Subjects> comboSubject;
    @FXML
    private TextField lectureTopic;
    private ProfessorsSubjectsInGroupsRepository psigr = new ProfessorsSubjectsInGroupsRepository();
    private LectureRepository lr = new LectureRepository();

    public void initialize() {
        Professors professor = (Professors) UserSession.getSession().getPerson();
        Set<Groups> groups = professor.getPsig().stream().map(ProfessorsSubjectsInGroups::getGroup).collect(Collectors.toSet());
        comboGroup.setItems(FXCollections.observableArrayList(groups));
        comboGroup.valueProperty().addListener((obs, oldValue, newValue) ->
                comboSubject.setItems(FXCollections.observableArrayList(psigr.findProfessorSubjectInGroup(newValue, professor))));
    }

    public void startLecture() {
        if (!validateTextFields()) {
        } else if (!validateComboBoxes()) {
        } else {
            Professors professor = (Professors) UserSession.getSession().getPerson();
            Lectures lecture = new Lectures(null, lectureTopic.getText(), LocalDateTime.now(), comboGroup.getValue(), comboSubject.getValue(),
                    professor);
            lr.save(lecture);
        }
    }

    public void cancelStarting() {
        lectureTopic.clear();
        comboGroup.getSelectionModel().clearSelection();
        comboSubject.getSelectionModel().clearSelection();
    }

    private boolean validateTextFields() {
        return checkIfEmpty(List.of(lectureTopic));
    }

    private boolean validateComboBoxes() {
        return checkIfValueInComboSelected(List.of(comboGroup, comboSubject));
    }
}
