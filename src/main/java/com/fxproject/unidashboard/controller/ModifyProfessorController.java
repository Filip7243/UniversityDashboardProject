package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.*;
import com.fxproject.unidashboard.validator.Validator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.fxproject.unidashboard.validator.Validator.*;

public class ModifyProfessorController {
    @FXML
    private AnchorPane personalData;
    @FXML
    private Spinner<Double> salary;
    @FXML
    private Spinner<Double> hourlyRate;
    @FXML
    private Spinner<Double> hoursWorked;
    @FXML
    private TitledPane addRemoveSubject;
    @FXML
    private TitledPane addWageTab;
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
    private ProfessorRepository pr = new ProfessorRepository();

    public void initialize() {
        salary.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(2750.0, 30000.0, 2750.0, 0.5));
        hourlyRate.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(15.0, 50.0, 15.0, 0.1));
        hoursWorked.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 250.0, 0.0, 1.0));

        salary.valueProperty().addListener((obs, oldValue, newValue) -> {
            System.out.println(validateSpinners());
        });

    }

    public void showAddRemoveForm() {
        comboGroups.setItems(FXCollections.observableArrayList(gr.findAllGroups()));
        comboSubjects.setItems(FXCollections.observableArrayList(sr.findAllSubjects()));
        Professors userData = getUserData();
        List<ProfessorsSubjectsInGroups> psig = userData.getPsig();
        List<Groups> g = psig.stream().map(ProfessorsSubjectsInGroups::getGroup).toList();
        comboRemoveGroup.setItems(FXCollections.observableArrayList(g));
        comboRemoveGroup.valueProperty().addListener((o, oldValue, newValue) ->
                comboRemoveSubject.setItems(FXCollections.observableArrayList(psigr.findProfessorSubjectInGroup(newValue, userData))));
    }

    public void addWage() {
        if(!validateSpinners()) {
            Wages wage = new Wages(null, salary.getValue(), hourlyRate.getValue(),
                    hoursWorked.getValue(), LocalDate.now(), getUserData());
            wr.addWage(wage);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Wage added!");
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Fields are empty! Try again!");
            a.show();
        }
    }

    public void addSubject() {
        if(validateComboBoxesInAdding()) {
            Professors userData = getUserData();
            ProfessorsSubjectsInGroups psig =
                    new ProfessorsSubjectsInGroups(userData, comboSubjects.getValue(), comboGroups.getValue());
            boolean flag = false;
            List<ProfessorsSubjectsInGroups> professorPSIG = psigr.findProfessorPSIG(userData);
            for (ProfessorsSubjectsInGroups i : professorPSIG) {
                if ((Objects.equals(i.getSubject().getId(), psig.getSubject().getId()) &&
                        Objects.equals(i.getGroup().getId(), psig.getGroup().getId()))) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                professorPSIG.add(psig);
                for (ProfessorsSubjectsInGroups professorsSubjectsInGroups : professorPSIG) {
                    System.out.println(professorsSubjectsInGroups.getGroup().getName());
                }
                psigr.save(psig);
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Added subject to professor");
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Professor already teaching this subject in this group!");
                a.show();
            }
            addRemoveSubject.expandedProperty().set(false);
        }

    }

    public void removeSubject() {
        if(validateComboBoxesInRemoving()) {
            Subjects subject = comboRemoveSubject.getValue();
            Groups group = comboRemoveGroup.getValue();
            Professors userData = getUserData();
            List<ProfessorsSubjectsInGroups> psig = psigr.findProfessorPSIG(userData);
            for (ProfessorsSubjectsInGroups professorsSubjectsInGroups : psig) {
                if (Objects.equals(professorsSubjectsInGroups.getSubject().getId(), subject.getId()) &&
                        Objects.equals(professorsSubjectsInGroups.getGroup().getId(), group.getId())) {
                    psigr.remove(professorsSubjectsInGroups);
                    break;
                }
            }
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Professor removed from group");
            a.show();
            addRemoveSubject.expandedProperty().set(false);
        }

    }

    private Professors getUserData() {
        Stage stage = (Stage) personalData.getScene().getWindow();
        Professors userData = (Professors) stage.getUserData();
        return pr.findProfessorByAlbumId(userData.getAlbumId()).orElseThrow();
    }

    public void cancelAddRemoveTab() {
        addRemoveSubject.expandedProperty().set(false);
    }

    public void cancelAddWageTab() {
        addWageTab.expandedProperty().set(false);
    }

    public void closeWindow(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    private boolean validateSpinners() {
        return checkIfSpinnerEmpty(salary) || checkIfSpinnerEmpty(hourlyRate) || checkIfSpinnerEmpty(hoursWorked);

    }

    private boolean validateComboBoxesInAdding() {
        return Validator.checkIfValueInComboSelected(List.of(comboGroups, comboSubjects));
    }

    private boolean validateComboBoxesInRemoving() {
        return Validator.checkIfValueInComboSelected(List.of(comboRemoveGroup, comboRemoveSubject));
    }
}
