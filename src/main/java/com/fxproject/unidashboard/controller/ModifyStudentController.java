package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.FieldsOfStudy;
import com.fxproject.unidashboard.model.Groups;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.repository.FieldOfStudyRepository;
import com.fxproject.unidashboard.repository.GroupRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.HibernateConnect;
import com.fxproject.unidashboard.validator.Validator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.fxproject.unidashboard.validator.Validator.checkIfValueInComboSelected;

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
        if (validateComboBoxes()) {
            Students userData = getUserData();
            AtomicBoolean flag = new AtomicBoolean(false);
            Set<Groups> studentGroups = userData.getGroups();
            for (Groups grp : studentGroups) {
                if (grp.getName().trim().equalsIgnoreCase(groups.valueProperty().get().getName().trim().toLowerCase())) {
                    flag.set(true);
                    break;
                }
            }

            if (flag.get()) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("WARNING");
                a.setHeaderText("Student already attends on this group");
                a.show();
            } else {
                sr.addStudentToGroup(userData.getAlbumId(), groups.valueProperty().get());

                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("CONFIRMATION");
                a.setHeaderText("Student added to group");
                a.show();
                addFieldOfStudyPane.expandedProperty().set(false);
            }
        }
    }

    private Students getUserData() {
        Stage stage = (Stage) personalData.getScene().getWindow();
        Long albumId = ((Students) stage.getUserData()).getAlbumId();
        return sr.findStudentByAlbumId(albumId).orElseThrow();
    }

    public void loadRemoveStudentFieldsOfStudy() {
        Students userData = getUserData();
        Set<Groups> studentGroups = userData.getGroups();
        removingGroups.setItems(FXCollections.observableArrayList(studentGroups));
    }

    public void removeGroup() {
        if (checkIfValueInComboSelected(List.of(removingGroups))) {
            Students userData = getUserData();
            Groups o = removingGroups.valueProperty().get();
            Set<Groups> grps = userData.getGroups();
            for (Groups group : grps) {
                if (group.getName().equals(o.getName())) {
                    grps.remove(group);
                    break;
                }
            }
            sr.updateStudent(userData);
            removeGroupPane.expandedProperty().set(false);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Student removed from group");
            a.show();
        }

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

    private boolean validateComboBoxes() {
        return checkIfValueInComboSelected(List.of(groups, fieldOfStudies));
    }

}
