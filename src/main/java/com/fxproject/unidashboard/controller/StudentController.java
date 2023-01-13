package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.StudentAttendanceOnLecture;
import com.fxproject.unidashboard.model.Groups;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.repository.GroupRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
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

public class StudentController {

    @FXML
    private Pane contentPane;
    @FXML
    private AnchorPane studentPanelPane;
    private static final FXMLLoader loader = new FXMLLoader();
    private static Person loggedInUser;
    private GroupRepository gr = new GroupRepository();

    public void showPersonalInfo() throws IOException {
        Stage stage = (Stage) studentPanelPane.getScene().getWindow();
        loggedInUser = UserSession.getSession((Person) stage.getUserData()).getPerson();
        contentPane.getChildren().clear();
        Node[] nodes = new Node[16];
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setBackground(Background.fill(Color.BLACK));
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
        vbox.setSpacing(10);
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

    public void showMarks() throws IOException {
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/student/student-marks.fxml").toURI().toURL();
        BorderPane borderPane = loader.load(url);
        contentPane.getChildren().add(borderPane);
    }

    public void showAttendance() throws IOException {
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/student/student-attendance.fxml").toURI().toURL();
        TableView<StudentAttendanceOnLecture> table = FXMLLoader.load(url);
        contentPane.getChildren().add(table);
    }

    public void showExams() throws IOException {
        contentPane.getChildren().clear();
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/student/calendar.fxml").toURI().toURL();
        AnchorPane anchor = FXMLLoader.load(url);

        contentPane.getChildren().add(anchor);
    }

    public void showFieldsOfStudies() throws IOException {
        contentPane.getChildren().clear();
        Stage stage = (Stage) studentPanelPane.getScene().getWindow();
        Students s = (Students) UserSession.getSession((Person) stage.getUserData()).getPerson();
        List<Groups> studentGroups = gr.findStudentGroups(s.getAlbumId());
        VBox box = new VBox();
        BorderPane title = new BorderPane();
        title.setPrefHeight(110);
        title.setBackground(Background.fill(Paint.valueOf("#191c24")));
        Label fieldsOfStudy = new Label("FIELDS OF STUDY");
        fieldsOfStudy.setFont(Font.font("System", FontWeight.BOLD, 40));
        fieldsOfStudy.setTextFill(Paint.valueOf("#aeaeae"));
        title.setCenter(fieldsOfStudy);
        box.getChildren().add(title);
        box.setPrefWidth(contentPane.getPrefWidth());
        box.setPrefHeight(contentPane.getPrefHeight());
        box.setAlignment(Pos.TOP_CENTER);
        box.setSpacing(20);
        for (Groups studentGroup : studentGroups) {
            HBox hBox = loadFXMLItem();
            Label label = (Label) hBox.lookup("#label");
            label.setText(studentGroup.getFieldOfStudy().getName());
            box.getChildren().add(hBox);
        }
        contentPane.getChildren().add(box);
    }

    public static HBox loadFXMLItem() throws IOException {
        String path = new File("").getAbsolutePath();
        URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/student/student-info.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        return (HBox) root.lookup("#infoItem");
    }
}
