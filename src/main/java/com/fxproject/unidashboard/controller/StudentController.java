package com.fxproject.unidashboard.controller;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class StudentController {

    @FXML
    private Pane contentPane;

    public void showPersonalInfo() throws IOException {
        contentPane.getChildren().clear();
        Node[] nodes = new Node[19];
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(contentPane.getPrefWidth());
        scrollPane.setPrefHeight(contentPane.getPrefHeight());
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: black");
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefWidth(contentPane.getPrefWidth());
        vbox.setPrefHeight(contentPane.getPrefHeight());
        scrollPane.setContent(vbox);
        contentPane.getChildren().add(scrollPane);
        for (int i = 0; i < nodes.length; i++) {
            HBox box = loadFXMLItem();
            Label label = (Label) box.lookup("#label");
            StackPane pane = (StackPane) box.lookup("#pane");
            switch (i) {
                case 0 -> {
                    label.setText("First Name");
                    Label firstName = new Label("Adam");
                    firstName.setFont(new Font(18));
                    firstName.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(firstName, Pos.CENTER);
                    pane.getChildren().add(firstName);
                }
                case 1 -> {
                    label.setText("Second Name");
                    Label secondName = new Label("Małysz");
                    secondName.setFont(new Font(18));
                    secondName.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(secondName, Pos.CENTER);
                    pane.getChildren().add(secondName);
                }
                case 2 -> label.setText("Last Name");
                case 3 -> label.setText("Email");
            }
            vbox.getChildren().add(box);
        }
    }

    public void showMarks() throws IOException {
        contentPane.getChildren().clear();
//        Accordion accordion = new Accordion();
//        accordion.setPrefWidth(contentPane.getPrefWidth());
//        accordion.setPrefHeight(contentPane.getPrefHeight());
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/student/student-marks.fxml").toURI().toURL();
        Accordion accordion = FXMLLoader.load(url);
        contentPane.getChildren().add(accordion);

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

    public void showAttendance() throws IOException {
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/student/student-attendance.fxml").toURI().toURL();
        JFXTreeTableView<?> table = FXMLLoader.load(url);
        contentPane.getChildren().add(table);
        // load student's attendance from db
        
    }

    private static HBox loadFXMLItem() throws IOException {
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/student/student-info.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        return (HBox) root.lookup("#infoItem");
    }
}
