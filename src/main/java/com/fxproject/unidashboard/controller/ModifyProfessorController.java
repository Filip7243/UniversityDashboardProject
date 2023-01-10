package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ModifyProfessorController {
    @FXML
    private AnchorPane personalData;
    @FXML
    private TextField salary;
    @FXML
    private TextField hourlyRate;
    @FXML
    private TextField hoursWorked;
    @FXML
    private TitledPane addRemoveSubject;
    @FXML
    private ComboBox<Groups> comboGroups;
    @FXML
    private ComboBox<Subjects> comboSubjects;
    @FXML
    private ComboBox<Groups> comboRemoveGroup;
    @FXML
    private ComboBox<Subjects> comboRemoveSubject;

    private WageRepository wr = new WageRepository();
    private GroupRepository gr = new GroupRepository();
    private SubjectRepository sr = new SubjectRepository();
    private ProfessorsSubjectsInGroupsRepository psigr = new ProfessorsSubjectsInGroupsRepository();

    public void initialize() {
        personalData.getChildren().forEach(System.out::println);
    }

    public void showAddRemoveForm() {
        comboGroups.setItems(FXCollections.observableArrayList(gr.findAllGroups()));
        comboSubjects.setItems(FXCollections.observableArrayList(sr.findAllSubjects()));
        Professors userData = getUserData();
        List<ProfessorsSubjectsInGroups> psig = userData.getPsig();
        List<Groups> g = psig.stream().map(ProfessorsSubjectsInGroups::getGroup).toList(); //todo: ogarnac te duplikaty zjebane
        comboRemoveGroup.setItems(FXCollections.observableArrayList(g));
        comboRemoveGroup.valueProperty().addListener((o, oldValue, newValue) ->
                comboRemoveSubject.setItems(FXCollections.observableArrayList(psigr.findProfessorSubjectInGroup(newValue, userData))));
    }

    public void addWage() {
        Wages wage = new Wages(null, Double.parseDouble(salary.getText()), Double.parseDouble(hourlyRate.getText()),
                Double.parseDouble(hoursWorked.getText()), LocalDateTime.now(), getUserData());
        wr.addWage(wage);
    }

    public void addSubject() {
        Professors userData = getUserData();
        ProfessorsSubjectsInGroups psig =
                new ProfessorsSubjectsInGroups(userData, comboSubjects.getValue(), comboGroups.getValue());
        boolean flag = false;
        for (ProfessorsSubjectsInGroups i : userData.getPsig()) {
            if ((Objects.equals(i.getSubject().getId(), psig.getSubject().getId()) &&
                    Objects.equals(i.getGroup().getId(), psig.getGroup().getId()))) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            userData.getPsig().add(psig);
            psigr.save(psig);
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Professor already teaching this subject in this group!");
            a.show();
        }
        addRemoveSubject.expandedProperty().set(false);
    }

    public void removeSubject() {
        Subjects subject = comboRemoveSubject.getValue();
        Groups group = comboRemoveGroup.getValue();
        Professors userData = getUserData();
        List<ProfessorsSubjectsInGroups> psig = userData.getPsig();
        for (ProfessorsSubjectsInGroups professorsSubjectsInGroups : psig) {
            if (Objects.equals(professorsSubjectsInGroups.getSubject().getId(), subject.getId()) &&
                    Objects.equals(professorsSubjectsInGroups.getGroup().getId(), group.getId())) {
                psigr.remove(professorsSubjectsInGroups);
                break;
            }
        }
        addRemoveSubject.expandedProperty().set(false);
    }

    private Professors getUserData() {
        Stage stage = (Stage) personalData.getScene().getWindow();
        return (Professors) stage.getUserData();
    }

    public void closeWindow(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
