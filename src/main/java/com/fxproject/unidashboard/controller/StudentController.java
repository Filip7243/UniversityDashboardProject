package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.HelloApplication;
import com.fxproject.unidashboard.model.Groups;
import com.fxproject.unidashboard.model.Marks;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.repository.GroupRepository;
import com.fxproject.unidashboard.repository.MarkRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class StudentController {

    @FXML
    private Pane contentPane;
    @FXML
    private AnchorPane studentPanelPane;
    @FXML
    private TextField searchBar;
    @FXML
    private Button closeButton;
    @FXML
    private Label userInfoLabel;
    private static final FXMLLoader loader = new FXMLLoader();
    private static Person loggedInUser;
    private GroupRepository gr = new GroupRepository();
    private MarkRepository mr = new MarkRepository();

    public void initialize() {
        UserSession session = UserSession.getSession();
        loggedInUser = session.getPerson();
        userInfoLabel.setText(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
    }

    public void showPersonalInfo() throws IOException {
        contentPane.getChildren().clear();
        searchBar.setDisable(true);
        Node[] nodes = new Node[15];
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
                    label.setText("Last Name");
                    Label l = new Label(loggedInUser.getLastName());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 2 -> {
                    label.setText("Email");
                    Label l = new Label(loggedInUser.getEmail());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 3 -> {
                    label.setText("Phone Number");
                    Label l = new Label(loggedInUser.getPhoneNumber());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 4 -> {
                    label.setText("Date Of Birth");
                    Label l = new Label(String.valueOf(loggedInUser.getBirthday().toLocalDate()));
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 5 -> {
                    label.setText("Place Of Birth");
                    Label l = new Label(loggedInUser.getPlaceOfBirth());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 6 -> {
                    label.setText("Pesel");
                    Label l = new Label(loggedInUser.getPesel());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 7 -> {
                    label.setText("Gender");
                    Label l = new Label(loggedInUser.getGender().toString());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 8 -> {
                    label.setText("Country");
                    Label l = new Label(loggedInUser.getAddress().getCountry());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 9 -> {
                    label.setText("City");
                    Label l = new Label(loggedInUser.getAddress().getCity());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 10 -> {
                    label.setText("Street");
                    Label l = new Label(loggedInUser.getAddress().getStreet());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 11 -> {
                    label.setText("House Number");
                    Label l = new Label(loggedInUser.getAddress().getHouseNumber().toString());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 12 -> {
                    label.setText("Flat Number");
                    Label l = new Label(loggedInUser.getAddress().getFlatNumber().toString());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 13 -> {
                    label.setText("Postal Code");
                    Label l = new Label(loggedInUser.getAddress().getPostalCode());
                    l.setFont(new Font(18));
                    l.setTextFill(Color.color(1, 1, 1));
                    StackPane.setAlignment(l, Pos.CENTER);
                    pane.getChildren().add(l);
                }
                case 14 -> {
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
        searchBar.setDisable(false);
        URL url = HelloApplication.class.getResource("fxml/student/student-marks.fxml");
        BorderPane borderPane = loader.load(url);
        contentPane.getChildren().add(borderPane);
    }

    public void searchMarks(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            Stage stage = (Stage) studentPanelPane.getScene().getWindow();
            loggedInUser = UserSession.getSession((Person) stage.getUserData()).getPerson();
            List<Marks> marks = mr.findWithName(searchBar.getText().trim().toLowerCase(),
                    ((Students) loggedInUser).getAlbumId());
            showSearchResult(marks);
        }
    }

    private void showSearchResult(List<Marks> marks) throws IOException {
        contentPane.getChildren().clear();
        if (!marks.isEmpty()) {
            BorderPane header = new BorderPane();
            header.setPrefHeight(110);
            header.setBackground(Background.fill(Paint.valueOf("#191c24")));
            Label marksLabel = new Label("MARKS");
            marksLabel.setFont(Font.font("System", FontWeight.BOLD, 40));
            marksLabel.setTextFill(Paint.valueOf("#aeaeae"));
            header.setCenter(marksLabel);
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(header);
            borderPane.setPrefWidth(contentPane.getPrefWidth());
            borderPane.setPrefHeight(contentPane.getPrefHeight());
            VBox marksBox = new VBox();
            marksBox.setAlignment(Pos.CENTER);
            marksBox.setPrefWidth(borderPane.getPrefWidth());

            for (int j = 0; j < marks.size(); j++) { // j < count(student's marks)
                HBox markInfoBox = loadFXMLItem();
                markInfoBox.setPrefWidth(0.9 * marksBox.getPrefWidth());
                marksBox.setSpacing(13);
                marksBox.getChildren().add(markInfoBox);
                StackPane sP = (StackPane) markInfoBox.lookup("#pane");
                Label markInfoLabel = (Label) markInfoBox.lookup("#label");
                Marks mark = marks.get(j);
                markInfoLabel.setText(
                        mark.getSubject().getName() + " | " +
                                mark.getType().name() + " " + "| " +
                                mark.getMarkDate().toString()
                );
                Label markLabel = new Label();
                markLabel.setText(String.valueOf(mark.getMark()));
                markLabel.setFont(new Font(18));
                markLabel.setTextFill(Color.color(0.5, 0, 1));
                StackPane.setAlignment(markLabel, Pos.CENTER);
                sP.getChildren().add(markLabel);
            }
            borderPane.setCenter(marksBox);
            contentPane.getChildren().add(borderPane);
        } else {
            Label empty = new Label("EMPTY");
            empty.setFont(new Font(40));
            empty.setTextFill(Paint.valueOf("#aeaeae"));
            empty.setPrefWidth(contentPane.getPrefWidth());
            empty.setPrefHeight(contentPane.getPrefHeight());
            empty.setAlignment(Pos.CENTER);
            contentPane.getChildren().add(empty);
        }
    }

    public void showAttendance() throws IOException {
        contentPane.getChildren().clear();
        searchBar.setDisable(true);
        URL url = HelloApplication.class.getResource("fxml/student/student-attendance.fxml");
        VBox table = FXMLLoader.load(url);
        contentPane.getChildren().add(table);
    }

    public void showExams() throws IOException {
        contentPane.getChildren().clear();
        searchBar.setDisable(true);
        URL url = HelloApplication.class.getResource("fxml/student/calendar.fxml");
        AnchorPane anchor = FXMLLoader.load(url);
        contentPane.getChildren().add(anchor);
    }

    public void showFieldsOfStudies() throws IOException {
        contentPane.getChildren().clear();
        searchBar.setDisable(true);
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
        URL url = HelloApplication.class.getResource("fxml/student/student-info.fxml");
        Parent root = FXMLLoader.load(url);
        return (HBox) root.lookup("#infoItem");
    }

    public void signOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("fxml/login-view.fxml"));
        Parent root = loader.load();
        Stage loginStage = new Stage();
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.setScene(new Scene(root, 1150, 650));
        loginStage.show();
        Stage stage = (Stage) (((Button) event.getSource()).getScene().getWindow());
        UserSession session = UserSession.getSession();
        if (session != null) {
            session.cancelSession();
        }
        stage.close();
    }

    public void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
