package com.fxproject.unidashboard.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

import static com.fxproject.unidashboard.controller.StudentController.loadFXMLItem;

public class StudentMarksController {

    @FXML
    private Accordion accordion;

    public void initialize() throws IOException {
        for (int i = 0; i < 10; i++) { // i < count(student's subjects)
            VBox marksBox = new VBox();
            for (int j = 0; j < 10; j++) { // j < count(student's marks in current subject(i))
                HBox markInfoBox = loadFXMLItem();
                marksBox.getChildren().add(markInfoBox);
                StackPane sP = (StackPane) markInfoBox.lookup("#pane");
                Label markInfoLabel = (Label) markInfoBox.lookup("#label");
                markInfoLabel.setText("KOLOKWIUM/EXAM");
                Label mark = new Label();
                mark.setText("5.0");
                mark.setFont(new Font(18));
                mark.setTextFill(Color.color(0.5, 0, 1));
                StackPane.setAlignment(mark, Pos.CENTER);
                sP.getChildren().add(mark);
            }
            TitledPane t = new TitledPane("Subject Name - Professor Name", marksBox);
            accordion.getPanes().add(t);
        }
    }
}
