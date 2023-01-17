package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.HelloApplication;
import com.fxproject.unidashboard.model.UniversityAccounts;
import com.fxproject.unidashboard.repository.AccountRepository;
import com.fxproject.unidashboard.utils.UserSession;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController {

    @FXML
    private JFXButton closeButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    private AccountRepository ar = new AccountRepository();

    public void initialize() {
    }

    public void logIn(ActionEvent event) {
        UniversityAccounts accounts = ar.checkIfUserExists(loginField.getText(), passwordField.getText());
        if (accounts != null) {
            Parent root;
            FXMLLoader loader = null;
            try {
                String role = accounts.getRole().name();
                switch (role) {
                    case "ROLE_ADMIN" ->
                            loader = new FXMLLoader(HelloApplication.class.getResource("fxml/admin/admin-menu.fxml"));
                    case "ROLE_PROFESSOR" ->
                            loader = new FXMLLoader(HelloApplication.class.getResource("fxml/professor/professor-main.fxml"));
                    case "ROLE_STUDENT" ->
                            loader = new FXMLLoader(HelloApplication.class.getResource("fxml/student/sudent-main.fxml"));

                }
                if (loader != null) {
                    UserSession session = UserSession.getSession(accounts.getPerson());
                    root = loader.load();
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(root, 1150, 650));
                    stage.setUserData(accounts.getPerson());
                    stage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Bad Username or Password");
            a.show();
        }
    }

    public void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
