package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.PersonDto;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StudentDetailsController {

    @FXML
    private Accordion details;
    @FXML
    private TitledPane personalData;
    @FXML
    private TitledPane accountData;
    @FXML
    private TitledPane addressData;

    public void initialize() {

    }

    public void loadPersonalData() {
        PersonDto userData = getDataFromStage();
        // get personal data from db
        VBox content = (VBox) personalData.getContent();
        ObservableList<Node> children = content.getChildren();
        for (Node child : children) {
            // child is hbox
            HBox i = (HBox) child;
            Label label = (Label) i.getChildren().get(0);
            switch (label.getText()) {
                case "First Name" -> {
                    Label firstName = (Label) i.getChildren().get(1);
                    firstName.setText(userData.getFirstName());
                } //todo: end this
                case "Second Name" -> ((Label) i.getChildren().get(1)).setText("ABCD");
            }

        }

    }

    public void loadAccountData() {
        PersonDto userData = getDataFromStage();
        // get account data from db
        VBox content = (VBox) accountData.getContent();
        ObservableList<Node> children = content.getChildren();
        for (Node child : children) {
            HBox i = (HBox) child;
            Label label = (Label) i.getChildren().get(0);
            switch (label.getText()) {
                case "University Email" -> ((Label) i.getChildren().get(1)).setText("ABCD");
                case "Created At" -> ((Label) i.getChildren().get(1)).setText("CDEFG");
                case "Active" -> ((Label) i.getChildren().get(1)).setText("SDAAS");
                case "Role" -> ((Label) i.getChildren().get(1)).setText("KIDSA");
            }
        }
    }

    public void loadAddressData() {
        PersonDto userData = getDataFromStage();
        // load address data from db
        VBox content = (VBox) addressData.getContent();
        ObservableList<Node> children = content.getChildren();
        for (Node child : children) {
            HBox i = (HBox) child;
            Label label = (Label) i.getChildren().get(0);
            switch (label.getText()) {
                case "Country" -> ((Label) i.getChildren().get(1)).setText("ABCD");
                case "City" -> ((Label) i.getChildren().get(1)).setText("CDEFG");
                case "Street" -> ((Label) i.getChildren().get(1)).setText("SDAAS");
                case "House Number" -> ((Label) i.getChildren().get(1)).setText("VX");
                case "Flat Number" -> ((Label) i.getChildren().get(1)).setText("KIDCXZCSA");
                case "Postal Code" -> ((Label) i.getChildren().get(1)).setText("SDADG");
            }
        }
    }

    private PersonDto getDataFromStage() {
        Stage stage = (Stage) details.getScene().getWindow();
        PersonDto userData = (PersonDto) stage.getUserData();
        return userData;
    }


}
