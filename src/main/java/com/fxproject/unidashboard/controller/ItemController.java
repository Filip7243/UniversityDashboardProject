package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.PersonDto;
import com.fxproject.unidashboard.dto.ProfessorDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ItemController {
    public void modifyItem(ActionEvent event) {
        Button btn = ((Button) (event.getSource()));
        Scene scene = btn.getScene();
        HBox lookup;
        try {
            lookup = (HBox) scene.lookup("#userItem");
            Label roleLabel = (Label) lookup.getChildren().get(4);
            String role = roleLabel.getText();
            switch (role) {
                case "Student" -> {
                    Stage stage = loadFXML(event, "modify-student.fxml");
                    PersonDto p = new PersonDto();
                    p.setFirstName("ABCD");
                    p.setLastName("SJAHGDSA");
                    stage.setUserData(p);
                }
                case "Professor" -> {
                    Stage stage = loadFXML(event, "modify-professor.fxml");
                    PersonDto p = new PersonDto();
                    p.setFirstName("ESSA");
                    p.setLastName("SJAHGDSA");
                    stage.setUserData(p);
                }
            }
        } catch (NullPointerException e) { // it means that this is lecture node

        }
    }

    public void showDetails(ActionEvent event) {
        Button btn = ((Button) (event.getSource()));
        Scene scene = btn.getScene();
        HBox lookup;
        try {
            lookup = (HBox) scene.lookup("#userItem");
            Label albumIdLabel = (Label) lookup.getChildren().get(3);
            String albumId = albumIdLabel.getText();
            PersonDto personDto = new PersonDto(); // todo: find from db by albumId
            Label roleLabel = (Label) lookup.getChildren().get(4);
            String role = roleLabel.getText();
            Stage stage;
            switch (role) {
                case "Student" -> {
                    stage = loadFXML(event, "student-details.fxml");
                    assert stage != null;
                    stage.setWidth(1004);
                    stage.setHeight(636);
                    stage.setX(300);
                    stage.setUserData(personDto);
                }
                case "Professor" -> {
                    stage = loadFXML(event, "professor-details.fxml");
                    assert stage != null;
                    stage.setWidth(1004);
                    stage.setHeight(636);
                    stage.setUserData(personDto);

                }
            }
        } catch (NullPointerException e) { // it means that this is lecture node
            lookup = (HBox) scene.lookup("#lectureItem");
            System.out.println("ESSA");
            Label idLabel = (Label) lookup.getChildren().get(0);
            String id = idLabel.getText();
            // find from db lecture with id and create LectureDto

            // load fxml
            Stage stage = loadFXML(event, "lecture-details.fxml");
            assert stage != null;
            stage.setWidth(1004);
            stage.setHeight(636);
            stage.setX(300);
//            stage.setUserData(); lecture dto pass here
        }
    }

    private static Stage loadFXML(ActionEvent event, String file) {
        Parent root;
        try {
            String path = new File("").getAbsolutePath();// todo: switch od roli i zaladowac wlasciwy fxml dla prof i stud osobny
            URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/admin/" + file).toURI().toURL();
            root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
