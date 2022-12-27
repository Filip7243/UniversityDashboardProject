package com.fxproject.unidashboard;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
    public JFXButton closeButton;
    public FontAwesomeIconView icon;
    @FXML
    private TextField idInput;
//    @FXML
//    private ListView<StudentDto> studentsDtoList;
    @FXML
    private Button findButton;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField secondNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private Button addButton;

    //todo: when removing student remove grades first similar to professor and subject
    public void initialize() {
//        ProfessorRepositoryImpl p = new ProfessorRepositoryImpl();
//        p.removeWithId(15L);
    }

    public void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    public void openSite() {
        //todo: open site
    }
}