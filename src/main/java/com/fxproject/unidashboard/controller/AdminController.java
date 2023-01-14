package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.HelloApplication;
import com.fxproject.unidashboard.dto.LectureDto;
import com.fxproject.unidashboard.dto.PersonDto;
import com.fxproject.unidashboard.model.Lectures;
import com.fxproject.unidashboard.model.Professors;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.repository.LectureRepository;
import com.fxproject.unidashboard.repository.ProfessorRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import static com.fxproject.unidashboard.mapper.LectureMapper.mapToLectureDtos;
import static com.fxproject.unidashboard.mapper.PersonMapper.mapToPersonDtos;

public class AdminController {
    @FXML
    private Label activeLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private Label totalUsers;
    @FXML
    private Label activeAccounts;
    @FXML
    private VBox itemsContainer; // contains students/professors/lectures etc.
    @FXML
    private Label loggedInUserName;
    @FXML
    private TextField searchBar;
    @FXML
    private Button closeButton;

    private StudentRepository sr = new StudentRepository();
    private ProfessorRepository profR = new ProfessorRepository();
    private LectureRepository lr = new LectureRepository();

    private Button clickedButton;

    public void initialize() {
//        Person person = UserSession.getSession().getPerson();
//        loggedInUserName.setText(person.getFirstName() + " " + person.getLastName());
    }

    private void loadUsersNodes(List<PersonDto> list) {
        searchBar.setDisable(false);
        Node[] nodes = new Node[list.size()];
        for (int i = 0; i < list.size(); i++) {
            try {
                URL url = HelloApplication.class.getResource("fxml/user-item.fxml");
                nodes[i] = FXMLLoader.load(url);
                HBox v = (HBox) nodes[i];
                BorderPane namePane = (BorderPane) v.getChildren().get(0);
                BorderPane lastNamePane = (BorderPane) v.getChildren().get(1);
                BorderPane emailPane = (BorderPane) v.getChildren().get(2);
                BorderPane albumIdPane = (BorderPane) v.getChildren().get(3);
                BorderPane rolePane = (BorderPane) v.getChildren().get(4);
                BorderPane isActivePane = (BorderPane) v.getChildren().get(5);
                HBox buttonsContainer = (HBox) v.getChildren().get(7);
                v.setId("userItem" + i);
                PersonDto p = list.get(i);
                Label firstName = (Label) namePane.getChildren().get(0);
                Label lastName = (Label) lastNamePane.getChildren().get(0);
                Label email = (Label) emailPane.getChildren().get(0);
                Label albumId = (Label) albumIdPane.getChildren().get(0);
                Label role = (Label) rolePane.getChildren().get(0);
                Label isActive = (Label) isActivePane.getChildren().get(0);
                Button modifyBtn = (Button) buttonsContainer.getChildren().get(0);
                Button deleteBtn = (Button) buttonsContainer.getChildren().get(1);
                Button detailsBtn = (Button) buttonsContainer.getChildren().get(2);
                modifyBtn.setId("modifyButton" + i);
                deleteBtn.setId("deleteButton" + i);
                detailsBtn.setId("detailsButton" + i);
                firstName.setText(p.getFirstName());
                lastName.setText(p.getLastName());
                email.setText(p.getEmail());
                albumId.setText(String.valueOf(p.getAlbumId()));
                role.setText(p.getRole().substring(5));
                isActive.setText(String.valueOf(p.isActive()));
                itemsContainer.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLecturesNodes(List<LectureDto> list) {
        searchBar.setDisable(false);
        Node[] nodes = new Node[list.size()];
        for (int i = 0; i < list.size(); i++) {
            try {
                URL url = HelloApplication.class.getResource("fxml/lecture-item.fxml");
                nodes[i] = FXMLLoader.load(url);
                HBox v = (HBox) nodes[i];
                BorderPane idPane = (BorderPane) v.getChildren().get(0);
                BorderPane topicPan = (BorderPane) v.getChildren().get(1);
                BorderPane datePane = (BorderPane) v.getChildren().get(2);
                BorderPane groupPane = (BorderPane) v.getChildren().get(3);
                BorderPane subjectPane = (BorderPane) v.getChildren().get(4);
                HBox buttonsContainer = (HBox) v.getChildren().get(6);
                Button deleteButton = (Button) buttonsContainer.getChildren().get(0);
                Button modifyButton = (Button) buttonsContainer.getChildren().get(1);
                deleteButton.setId("deleteButton" + i);
                modifyButton.setId("modifyButton" + i);
                v.setPrefWidth(800.0);
                v.setId("lectureItem" + i);
                // lectures
                LectureDto lectureDto = list.get(i);
                Label id = (Label) idPane.getChildren().get(0);
                Label topic = (Label) topicPan.getChildren().get(0);
                Label date = (Label) datePane.getChildren().get(0);
                Label group = (Label) groupPane.getChildren().get(0);
                Label subject = (Label) subjectPane.getChildren().get(0);
                id.setText(String.valueOf(lectureDto.getId()));
                topic.setText(lectureDto.getTopic());
                date.setText(lectureDto.getDate().toString());
                group.setText(lectureDto.getGroupName());
                subject.setText(lectureDto.getSubjectName());

                itemsContainer.getChildren().addAll(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int countActiveAccounts(List<PersonDto> list) {
        int counter = 0;
        for (PersonDto personDto : list) {
            if (personDto.isActive()) {
                counter++;
            }
        }
        return counter;
    }

    public void showStudents(ActionEvent event) {
        clickedButton = (Button) event.getSource();
        // load all students from db
        List<Students> allStudents = sr.findAllStudents();
        loadDtos(mapToPersonDtos(allStudents));
    }

    public void showProfessors(ActionEvent event) {
        clickedButton = (Button) event.getSource();
        // load all professors from db
        List<Professors> allProfessors = profR.findAllProfessors();
        loadDtos(mapToPersonDtos(allProfessors));
    }

    private void loadDtos(List<PersonDto> personDtos) {
        totalUsers.setText(String.valueOf(personDtos.size()));
        activeAccounts.setText(String.valueOf(countActiveAccounts(personDtos)));
        totalLabel.setText("Total Users");
        activeLabel.setText("Active Accounts");
        itemsContainer.getChildren().clear();
        loadUsersNodes(personDtos);
    }

    public void showLectures(ActionEvent event) {
        clickedButton = (Button) event.getSource();
        System.out.println(clickedButton.getText());
        List<Lectures> allLectures = lr.findAllLectures();
        loadLecturesDtos(mapToLectureDtos(allLectures));
    }

    private void loadLecturesDtos(List<LectureDto> lectureDtos) {
        totalUsers.setText(String.valueOf(lectureDtos.size())); // total lectures
        activeAccounts.setText(String.valueOf(countCurrentLessons(lr.findAllLectures())));
        totalLabel.setText("All Lectures");
        activeLabel.setText("Now Lessons");
        itemsContainer.getChildren().clear();
        loadLecturesNodes(lectureDtos);
    }

    private int countCurrentLessons(List<Lectures> lectures) {
        int counter = 0;
        for (Lectures lecture : lectures) {
            LocalDateTime lectureDate = lecture.getLectureDate();
            if (lectureDate.isAfter(LocalDateTime.now().minusMinutes(90))) {
                counter++;
            }
        }

        return counter;
    }

    public void showAddingForm(ActionEvent event) {
        searchBar.setDisable(true);
        Parent root;
        FXMLLoader loader = null;
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "addProfessorButton" ->
                        loader = new FXMLLoader(HelloApplication.class.getResource("fxml/admin/add-professor-form.fxml"));
                case "addStudentButton" ->
                        loader = new FXMLLoader(HelloApplication.class.getResource("fxml/admin/add-student-form.fxml"));
            }

            if (loader != null) {
                root = loader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root, 725, 383));
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (clickedButton != null) {
                switch (clickedButton.getText()) {
                    case "Students" -> {
                        List<Students> studentWithName = sr.findStudentWithName(searchBar.getText().trim().toLowerCase());
                        loadDtos(mapToPersonDtos(studentWithName));
                    }
                    case "Professors" -> loadDtos(mapToPersonDtos(profR.findStudentWithName(searchBar.getText())));
                    case "Lectures" -> loadLecturesDtos(mapToLectureDtos(lr.findLectureWithTopic(searchBar.getText())));
                }
            }
        }
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
