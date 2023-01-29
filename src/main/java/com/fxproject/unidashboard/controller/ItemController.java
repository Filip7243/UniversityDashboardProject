package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.HelloApplication;
import com.fxproject.unidashboard.model.Lectures;
import com.fxproject.unidashboard.model.Professors;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.repository.LectureRepository;
import com.fxproject.unidashboard.repository.PersonRepository;
import com.fxproject.unidashboard.repository.ProfessorRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class ItemController {

    private StudentRepository sr = new StudentRepository();
    private ProfessorRepository pr = new ProfessorRepository();
    private LectureRepository lr = new LectureRepository();

    private PersonRepository personRepository = new PersonRepository();

    public void initialize() {
    }

    public void modifyItem(ActionEvent event) {
        Button btn = ((Button) (event.getSource()));
        Scene scene = btn.getScene();
        HBox lookup;
        try {
            String nodeId = btn.getId().substring(12); // 12 - length of string modifyButton
            lookup = (HBox) scene.lookup("#userItem" + nodeId);
            BorderPane albumIdLabelPane = (BorderPane) lookup.getChildren().get(3);
            String albumId = ((Label) albumIdLabelPane.getChildren().get(0)).getText();
            BorderPane roleLabelPane = (BorderPane) lookup.getChildren().get(4);
            String role = ((Label) roleLabelPane.getChildren().get(0)).getText();
            switch (role.toLowerCase()) {
                case "student" -> {
                    Students student = sr.findStudentByAlbumId(Long.parseLong(albumId))
                            .orElseThrow(() -> {
                                Alert alert = new Alert(Alert.AlertType.WARNING, "Student doesn't exists");
                                alert.show();
                                return new RuntimeException(alert.getContentText());
                            });
                    Stage stage = loadFXML(event, "modify-student.fxml");
                    assert stage != null;
                    stage.setUserData(student);
                }
                case "professor" -> {
                    Professors professor = pr.findProfessorByAlbumId(Long.parseLong(albumId))
                            .orElseThrow(() -> {
                                Alert alert = new Alert(Alert.AlertType.WARNING, "Student doesn't exists");
                                alert.show();
                                return new RuntimeException(alert.getContentText());
                            });
                    Stage stage = loadFXML(event, "modify-professor.fxml");
                    assert stage != null;
                    stage.setUserData(professor);
                }
            }
        } catch (NullPointerException e) {
            // it means it is lecture node (future feature)
        }
    }

    public void showDetails(ActionEvent event) {
        Button btn = ((Button) (event.getSource()));
        Scene scene = btn.getScene();
        HBox lookup;
        try {
            String nodeId = btn.getId().substring(13); // 13 is length of sting detailsButton
            lookup = (HBox) scene.lookup("#userItem" + nodeId);
            BorderPane albumIdLabelPane = (BorderPane) lookup.getChildren().get(3);
            String albumId = ((Label) albumIdLabelPane.getChildren().get(0)).getText();
            BorderPane roleLabelPane = (BorderPane) lookup.getChildren().get(4);
            String role = ((Label) roleLabelPane.getChildren().get(0)).getText();
            Stage stage;
            switch (role.toLowerCase()) {
                case "student" -> {
                    Students student = sr.findStudentByAlbumId(Long.parseLong(albumId))
                            .orElseThrow(() -> {
                                Alert alert = new Alert(Alert.AlertType.WARNING, "Student doesn't exists");
                                alert.show();
                                return new RuntimeException(alert.getContentText());
                            });
                    stage = loadFXML(event, "student-details.fxml");
                    assert stage != null;
                    stage.setWidth(1100);
                    stage.setHeight(780);
                    stage.setY(25);
                    stage.setUserData(student);
                }
                case "professor" -> {
                    Professors professor = pr.findProfessorByAlbumId(Long.parseLong(albumId))
                            .orElseThrow(() -> {
                                Alert alert = new Alert(Alert.AlertType.WARNING, "Professor doesn't exists");
                                alert.show();
                                return new RuntimeException(alert.getContentText());
                            });
                    stage = loadFXML(event, "professor-details.fxml");
                    assert stage != null;
                    stage.setWidth(1100);
                    stage.setHeight(780);
                    stage.setY(25);
                    stage.setUserData(professor);
                }
            }
        } catch (NullPointerException e) { // it means that this is lecture node
            String nodeId = btn.getId().substring(btn.getId().length() - 1);
            lookup = (HBox) scene.lookup("#lectureItem" + nodeId);
            BorderPane idPane = (BorderPane) lookup.getChildren().get(0);
            Label idLabel = (Label) idPane.getChildren().get(0);
            // find from db lecture with id and create LectureDto
            Lectures lectures = lr.findLectureWithId(Long.parseLong(idLabel.getText()))
                    .orElseThrow(() -> {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Lecture doesn't exists");
                        alert.show();
                        return new RuntimeException(alert.getContentText());
                    });
            // load fxml
            Stage stage = loadFXML(event, "lecture-details.fxml");
            assert stage != null;
            stage.setWidth(1012);
            stage.setHeight(636);
            stage.setX(300);
            stage.setUserData(lectures);
        }
    }

    public void deleteItem(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.WARNING, "You sure you want to delete it?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                // OK was pressed
                Button btn = ((Button) (event.getSource()));
                Scene scene = btn.getScene();
                HBox lookup;
                try {
                    String nodeId = btn.getId().substring(12); // 12 length of string deleteButton
                    lookup = (HBox) scene.lookup("#userItem" + nodeId);
                    BorderPane albumIdLabelPane = (BorderPane) lookup.getChildren().get(3);
                    String albumId = ((Label) albumIdLabelPane.getChildren().get(0)).getText();
                    BorderPane roleLabelPane = (BorderPane) lookup.getChildren().get(4);
                    String role = ((Label) roleLabelPane.getChildren().get(0)).getText();
                    VBox vbox = (VBox) scene.lookup("#itemsContainer");
                    switch (role.toLowerCase()) {
                        case "student" -> {
                            Students student = sr.findStudentByAlbumId(Long.parseLong(albumId))
                                    .orElseThrow(() -> {
                                        Alert alert = new Alert(Alert.AlertType.WARNING, "Student doesn't exists");
                                        alert.show();
                                        return new RuntimeException(alert.getContentText());
                                    });
                            personRepository.removePersonWithId(student.getId());
                        }
                        case "professor" -> {
                            Professors professor = pr.findProfessorByAlbumId(Long.parseLong(albumId))
                                    .orElseThrow(() -> {
                                        Alert alert = new Alert(Alert.AlertType.WARNING, "Professor doesn't exists");
                                        alert.show();
                                        return new RuntimeException(alert.getContentText());
                                    });
                            personRepository.removePersonWithId(professor.getId());
                        }
                    }
                    vbox.getChildren().remove(lookup);
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Person Deleted");
                    a.show();
                } catch (NullPointerException e) { // lecture node
                    String nodeId = btn.getId().substring(btn.getId().length() - 1);
                    lookup = (HBox) scene.lookup("#lectureItem" + nodeId);
                    VBox vbox = (VBox) scene.lookup("#itemsContainer");
                    BorderPane idPane = (BorderPane) lookup.getChildren().get(0);
                    Label idLabel = (Label) idPane.getChildren().get(0);
                    Lectures lectures = lr.findLectureWithId(Long.parseLong(idLabel.getText()))
                            .orElseThrow(() -> {
                                Alert alert = new Alert(Alert.AlertType.WARNING, "Lecture doesn't exists");
                                alert.show();
                                return new NullPointerException(alert.getContentText());
                            });
                    lr.removeLecture(lectures);
                    vbox.getChildren().remove(lookup);
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Lecture Deleted");
                    a.show();
                }
            }
        }


    }

    private static Stage loadFXML(ActionEvent event, String file) {
        Parent root;
        try {
            URL url = HelloApplication.class.getResource("fxml/admin/" + file);
            root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 802.4, 610));
            stage.show();
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
