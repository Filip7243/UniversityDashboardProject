package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.PersonDto;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.stage.Stage;
import javafx.stage.Window;

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
