package com.fxproject.unidashboard.controller;

import javafx.collections.ObservableList;
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
            Label albumIdLabel = (Label) lookup.getChildren().get(4);
            String albumId = albumIdLabel.getText();
            switch (albumId) {
                case "Student" -> loadFXML(event, "modify-student-view.fxml");
                case "Professor" -> loadFXML(event, "modify-professor-view.fxml");
            }
        } catch (NullPointerException e) { // it means that this is lecture node
            try {
                lookup = (HBox) scene.lookup("#lectureItem");
                loadFXML(event, "modify-lecture-view.fxml");
            } catch (NullPointerException exc) {
                e.printStackTrace(); // todo: better catch load main window
            }
        }
    }

    private static void loadFXML(ActionEvent event, String file) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
