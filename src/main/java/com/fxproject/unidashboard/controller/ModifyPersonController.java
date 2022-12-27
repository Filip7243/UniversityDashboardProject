package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.PersonDto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ModifyPersonController {

    @FXML
    private TitledPane personalData;
    @FXML
    private AnchorPane personalDetails;
    @FXML
    private TextField firstName;
    @FXML
    private TextField universityEmail;

    public void loadPersonalData() {
        PersonDto user = getDataFromStage();
//        firstName.setText(user.getFirstName());
    }

    public void loadAccountData() {
        PersonDto user = getDataFromStage();
//        universityEmail.setText(user.getLastName());
    }

    public void loadStudentFieldsOfStudy() {
        System.out.println("TEST");
    }

    private PersonDto getDataFromStage() {
        Stage stage = (Stage) personalDetails.getScene().getWindow();
        PersonDto userData = (PersonDto) stage.getUserData();
        return userData;
    }
}
