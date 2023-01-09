package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.LectureRepository;
import com.fxproject.unidashboard.repository.ProfessorsSubjectsInGroupsRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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
        Professors professor = (Professors) UserSession.getSession().getPerson();
        Lectures lecture = new Lectures(null, lectureTopic.getText(), LocalDateTime.now(), comboGroup.getValue(), comboSubject.getValue(),
                professor);
        lr.save(lecture);
//        AnchorPane parent = (AnchorPane) container.getParent().getParent();
//        Button attendanceBtn = (Button) parent.lookup("#attendanceButton");
//        attendanceBtn.setUserData(lecture);
//        attendanceBtn.fire();
//        attendanceBtn.setDisable(true);
    }

    public void cancelStarting() {
        System.out.println("CANCEL");
    }
}
