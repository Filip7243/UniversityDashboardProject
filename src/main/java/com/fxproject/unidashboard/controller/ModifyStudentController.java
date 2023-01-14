package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.FieldsOfStudy;
import com.fxproject.unidashboard.model.Groups;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.repository.FieldOfStudyRepository;
import com.fxproject.unidashboard.repository.GroupRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ModifyStudentController {

    @FXML
    private AnchorPane personalData;
    @FXML
    private TitledPane addFieldOfStudyPane;
    @FXML
    private TitledPane removeGroupPane;
    @FXML
    private ComboBox<FieldsOfStudy> fieldOfStudies;
    @FXML
    private ComboBox<Groups> groups;
    @FXML
    private ComboBox<Groups> removingGroups;
    private FieldOfStudyRepository fr = new FieldOfStudyRepository();
    private GroupRepository gr = new GroupRepository();
    private StudentRepository sr = new StudentRepository();

    public void initialize() {

    }

    public void loadStudentFieldsOfStudy() {
        // load fieldsOfStudy from DB
        List<FieldsOfStudy> allFieldsOfStudy = fr.findAllFieldsOfStudy();
        fieldOfStudies.setItems(FXCollections.observableArrayList(allFieldsOfStudy));

        fieldOfStudies.valueProperty().addListener((obs, oldValue, newValue) -> {
            groups.setItems(FXCollections.observableArrayList(gr.findGroupsByFieldOfStudy(newValue)));
            groups.setVisible(true);
        });
    }

    public void addFieldOfStudy() {
        // check if students already in fieldOfStudy
        Students userData = getUserData();
        AtomicBoolean flag = new AtomicBoolean(false);
        userData.getGroups().forEach(grp -> {
            if (grp.getName().trim().equalsIgnoreCase(groups.valueProperty().get().getName().trim().toLowerCase())) {
                flag.set(true);
            }
        });
        if (flag.get()) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("Student already attends on this group");
            a.show();
        } else {
            userData.getGroups().add(groups.valueProperty().get());
            sr.updateStudent(userData);
        }
    }

    private Students getUserData() {
        Stage stage = (Stage) personalData.getScene().getWindow();
        return (Students) stage.getUserData();
    }

    public void loadRemoveStudentFieldsOfStudy() {
        Students userData = getUserData();
        removingGroups.setItems(FXCollections.observableArrayList(userData.getGroups()));
    }

    public void removeGroup() {
        Students userData = getUserData();
        userData.getGroups().remove(removingGroups.valueProperty().get());
        sr.updateStudent(userData);
        removeGroupPane.expandedProperty().set(false);
    }

    public void cancelRemoving() {
        removeGroupPane.expandedProperty().set(false);
    }

    public void cancelAdding() {
        addFieldOfStudyPane.expandedProperty().set(false);
    }

    public void closeWindow(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
