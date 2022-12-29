package com.fxproject.unidashboard.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ModifyProfessorController {
    @FXML
    public AnchorPane personalData;

    public void initialize() {
        personalData.getChildren().forEach(System.out::println);
    }
}
