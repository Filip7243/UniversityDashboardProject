package com.fxproject.unidashboard.controller;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;

public class StudentController {

    @FXML
    private Pane contentPane;
    private static final FXMLLoader loader = new FXMLLoader();

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
        Accordion accordion = loader.load(url);
        contentPane.getChildren().add(accordion);


    }

    public void showAttendance() throws IOException {
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        // todo: it should be in fxml's controller
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/student/student-attendance.fxml").toURI().toURL();
        JFXTreeTableView<?> table = FXMLLoader.load(url);
        contentPane.getChildren().add(table);
        // load student's attendance from db
    }

    public void showExams() throws IOException {
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/student/calendar.fxml").toURI().toURL();
        AnchorPane anchor = FXMLLoader.load(url);

        contentPane.getChildren().add(anchor);

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (MonthDay.from(item).equals(MonthDay.of(3, 15)) &&
                                !(getStyleClass().contains("next-month") || getStyleClass().contains("previous-month")) &&
                                Year.from(item).equals(Year.of(2022))
                        ) {
                            setTooltip(new Tooltip("Beware the Ides of March!"));
                            setStyle("-fx-background-color: #ff4444;");
                            setText("Exam Algebra");
                            setWrapText(true);
                            setTextAlignment(TextAlignment.CENTER);
                        } else {
                            setTooltip(null);
                            setStyle(null);
                        }
                    }
                };
            }
        };

        DatePicker dp = new DatePicker();
        dp.setDayCellFactory(dayCellFactory);
        DatePickerSkin dpSkin = new DatePickerSkin(dp);
        Node popupContent = dpSkin.getPopupContent();
        anchor.getChildren().add(popupContent);
    }

    public void showFieldsOfStudies() throws IOException {
        contentPane.getChildren().clear();
        VBox box = new VBox();
        box.setPrefWidth(contentPane.getPrefWidth());
        box.setPrefHeight(contentPane.getPrefHeight());
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        for (int i = 0; i < 3; i++) { // i < number of student's fields of studies
            HBox hBox = loadFXMLItem();
            Label label = (Label) hBox.lookup("#label");
            label.setText("FIELD OF STUDY NAME");
            box.getChildren().add(hBox);
        }
        contentPane.getChildren().add(box);
    }

    static HBox loadFXMLItem() throws IOException {
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/student/student-info.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        return (HBox) root.lookup("#infoItem");
    }
}
