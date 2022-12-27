package com.fxproject.unidashboard.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.stage.Stage;

public class LectureDetailsController {

    @FXML
    private Accordion details;

    public void loadLectureData() {
        Stage stage = (Stage) details.getScene().getWindow();
//        PersonDto lectureDataDTO = (PersonDto) stage.getUserData();
//        System.out.println(userData.getFirstName());
        // populate data from lectureDataDTO
    }


}
