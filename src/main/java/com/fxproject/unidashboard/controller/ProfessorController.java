package com.fxproject.unidashboard.controller;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.fxproject.unidashboard.controller.StudentController.loadFXMLItem;

public class ProfessorController {

    @FXML
    private Pane contentPane;
    private static final FXMLLoader loader = new FXMLLoader();

    public void showPersonalInfo() throws IOException {
        contentPane.getChildren().clear();

        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/personal-info.fxml").toURI().toURL();
        ScrollPane scrollPane = loader.load(url);
        VBox box = (VBox) scrollPane.getContent();

        for(int i = 0; i < 19; i++) {
            HBox hBox = loadFXMLItem();
            Label label = (Label) hBox.lookup("#label");
            label.setText("FIRST NAME, LAST NAME etc.");
            box.getChildren().add(hBox);
        }

        contentPane.getChildren().add(scrollPane);
    }

    public void showGradeStudentForm() throws IOException {
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/professor/grade-student-form.fxml").toURI().toURL();
        AnchorPane anchorPane = loader.load(url);
        // test data
        ObservableList<String> groups = FXCollections.observableArrayList(List.of("A", "B", "C"));

        ObservableList<String> subjectsGroupA = FXCollections.observableArrayList(List.of("z", "x"));
        ObservableList<String> subjectsGroupB = FXCollections.observableArrayList(List.of("z", "m"));
        ObservableList<String> subjectsGroupC = FXCollections.observableArrayList(List.of("z"));

        ObservableList<String> studentsGroupA = FXCollections.observableArrayList(List.of("L", "M", "N"));
        ObservableList<String> studentsGroupB = FXCollections.observableArrayList(List.of("P", "O"));
        ObservableList<String> studentsGroupC = FXCollections.observableArrayList(List.of("K"));

        ComboBox<String> comboGroups = (ComboBox<String>) anchorPane.lookup("#comboGroups");
        ComboBox<String> comboSubjects = (ComboBox<String>) anchorPane.lookup("#comboSubjects");
        ComboBox<String> comboStudents = (ComboBox<String>) anchorPane.lookup("#comboStudents");
        comboGroups.setItems(groups);

        comboSubjects.setVisible(false);
        comboStudents.setVisible(false);

        comboGroups.valueProperty().addListener((e, oldValue, newValue) -> {
            switch (newValue) {
                case "A" -> {
                    comboSubjects.setVisible(true);
                    comboStudents.setVisible(true);
                    comboSubjects.setItems(subjectsGroupA);
                    comboStudents.setItems(studentsGroupA);
                }
                case "B" -> {
                    comboSubjects.setVisible(true);
                    comboStudents.setVisible(true);
                    comboSubjects.setItems(subjectsGroupB);
                    comboStudents.setItems(studentsGroupB);
                }
                case "C" -> {
                    comboSubjects.setVisible(true);
                    comboStudents.setVisible(true);
                    comboSubjects.setItems(subjectsGroupC);
                    comboStudents.setItems(studentsGroupC);
                }
            }
        });

        contentPane.getChildren().add(anchorPane);
        // text field for grade etc...
    }

    public void showStartLectureForm() throws IOException {
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/professor/start-lecture.fxml")
                .toURI().toURL();
        VBox vbox = loader.load(url);
        contentPane.getChildren().add(vbox);
    }

    public void showCheckAttendanceForm() throws IOException {
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/professor/attendance.fxml").toURI().toURL();
        JFXTreeTableView<?> attendanceList = loader.load(url);
        contentPane.getChildren().add(attendanceList);
    }

    public void showAddExamForm() throws IOException {
        // load fxml
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/professor/add-exam.fxml").toURI().toURL();
        VBox vbox = loader.load(url);
        contentPane.getChildren().add(vbox);
        //todo: dodac tabele exam
        // name
        // group
        // subject
        // type
        // date
    }

    public void closeWindow() {
        System.out.println("close");
    }
}
