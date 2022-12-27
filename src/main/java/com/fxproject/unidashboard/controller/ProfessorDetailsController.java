package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.PersonDto;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfessorDetailsController {
    @FXML
    private Accordion details;
    @FXML
    private TitledPane professorData;

    public void loadProfessorData() {
        PersonDto userData = getDataFromStage();
        VBox content = (VBox) professorData.getContent();
        ObservableList<Node> children = content.getChildren();
        JFXTreeTableView<?> subjectsGroups = (JFXTreeTableView<?>) children.get(0); // ? = dto with subject and professors gorup
        //tood: populate data etc...

    }

    private PersonDto getDataFromStage() {
        Stage stage = (Stage) details.getScene().getWindow();
        PersonDto userData = (PersonDto) stage.getUserData();
        return userData;
    }

    public void loadPersonalData(MouseEvent event) {
        
    }

    public void loadAccountData(MouseEvent event) {
    }

    public void loadAddressData(MouseEvent event) {
    }
}
