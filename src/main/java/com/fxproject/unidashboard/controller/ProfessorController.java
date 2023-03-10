package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.Lectures;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.utils.UserSession;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.fxproject.unidashboard.controller.StudentController.loadFXMLItem;

public class ProfessorController {

    @FXML
    private Button attendanceButton;
    @FXML
    private Pane contentPane;
    @FXML
    private AnchorPane professorPanelPane;
    private static Person loggedInUser;

    private static final FXMLLoader loader = new FXMLLoader();

    public void showPersonalInfo() throws IOException {
        Stage stage = (Stage) professorPanelPane.getScene().getWindow();
        loggedInUser = UserSession.getSession((Person) stage.getUserData()).getPerson();
        contentPane.getChildren().clear();
        Node[] nodes = new Node[16];
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(contentPane.getPrefWidth());
        scrollPane.setPrefHeight(contentPane.getPrefHeight());
        VBox vbox = new VBox();
        BorderPane title = new BorderPane();
        title.setPrefHeight(110);
        title.setBackground(Background.fill(Paint.valueOf("#191c24")));
        Label personalInfo = new Label("Personal Info");
        personalInfo.setFont(Font.font("System", FontWeight.BOLD, 40));
        personalInfo.setTextFill(Paint.valueOf("#aeaeae"));
        title.setCenter(personalInfo);
        vbox.getChildren().add(title);
        vbox.setStyle("-fx-background-color: black");
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPrefWidth(contentPane.getPrefWidth());
        vbox.setPrefHeight(contentPane.getPrefHeight());
        scrollPane.setContent(vbox);
        contentPane.getChildren().add(scrollPane);
        for (int i = 0; i < nodes.length; i++) {
            HBox box = loadFXMLItem();
            box.setPrefWidth(700.0);
            Label label = (Label) box.lookup("#label");
            StackPane pane = (StackPane) box.lookup("#pane");
            switch (i) {
                case 0 -> {
                    label.setText("First Name");
                    Label l = new Label(loggedInUser.getFirstName());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 1 -> {
                    label.setText("Second Name");
                    Label l = new Label("DO USUNIECIA");
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 2 -> {
                    label.setText("Last Name");
                    Label l = new Label(loggedInUser.getLastName());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 3 -> {
                    label.setText("Email");
                    Label l = new Label(loggedInUser.getEmail());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 4 -> {
                    label.setText("Phone Number");
                    Label l = new Label(loggedInUser.getPhoneNumber());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 5 -> {
                    label.setText("Date Of Birth");
                    Label l = new Label(String.valueOf(loggedInUser.getBirthday().toLocalDate()));
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 6 -> {
                    label.setText("Place Of Birth");
                    Label l = new Label(loggedInUser.getPlaceOfBirth());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 7 -> {
                    label.setText("Pesel");
                    Label l = new Label(loggedInUser.getPesel());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 8 -> {
                    label.setText("Gender");
                    Label l = new Label(loggedInUser.getGender().toString());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 9 -> {
                    label.setText("Country");
                    Label l = new Label(loggedInUser.getAddress().getCountry());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 10 -> {
                    label.setText("City");
                    Label l = new Label(loggedInUser.getAddress().getCity());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 11 -> {
                    label.setText("Streey");
                    Label l = new Label(loggedInUser.getAddress().getStreet());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 12 -> {
                    label.setText("House Number");
                    Label l = new Label(loggedInUser.getAddress().getHouseNumber().toString());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 13 -> {
                    label.setText("Flat Number");
                    Label l = new Label(loggedInUser.getAddress().getFlatNumber().toString());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 14 -> {
                    label.setText("Postal Code");
                    Label l = new Label(loggedInUser.getAddress().getPostalCode());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 15 -> {
                    label.setText("University Email");
                    Label l = new Label(loggedInUser.getAcc().getUniversityEmail());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
            }
            vbox.getChildren().add(box);
        }
    }

    public void showGradeStudentForm() throws IOException {
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/professor/grade-student-form.fxml").toURI().toURL();
        AnchorPane anchorPane = loader.load(url);
        contentPane.getChildren().add(anchorPane);
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
        VBox vbox = loader.load(url);
        contentPane.getChildren().add(vbox);
    }

    public void showAddExamForm() throws IOException {
        // load fxml
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/professor/add-exam.fxml").toURI().toURL();
        VBox vbox = loader.load(url);
        contentPane.getChildren().add(vbox);
    }

    public void closeWindow() {
        System.out.println("close");
    }
}
