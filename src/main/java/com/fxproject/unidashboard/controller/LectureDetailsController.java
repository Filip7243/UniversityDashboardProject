package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.Lectures;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LectureDetailsController {

    @FXML
    private Label dateLabel;

    @FXML
    private Accordion details;

    @FXML
    private Label groupLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label subjectLabel;

    @FXML
    private Label topicLabel;


    public void loadLectureData() {
        Stage stage = (Stage) details.getScene().getWindow();
        Lectures lecture = (Lectures) stage.getUserData();
        idLabel.setText(String.valueOf(lecture.getId()));
        topicLabel.setText(lecture.getLectureTopic());
        dateLabel.setText(String.valueOf(lecture.getLectureDate().toLocalDate()));
        groupLabel.setText(lecture.getGroup().getName());
        subjectLabel.setText(lecture.getSubject().getName());
    }

    public void closeWindow(ActionEvent event) {
        ((Stage) ((Button) (event.getSource())).getScene().getWindow()).close();
    }


}
