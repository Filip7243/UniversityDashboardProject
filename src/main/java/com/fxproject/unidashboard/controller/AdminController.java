package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.PersonDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AdminController {
    @FXML
    private Label totalUsers;
    @FXML
    private Label activeAccounts;
    //    @FXML
//    private JFXButton closeButton;
//    @FXML
//    private TableView<PersonDto> people;
//    @FXML
//    private TableColumn<PersonDto, String> firstNameCol;
//    @FXML
//    private TableColumn<PersonDto, String> lastNameCol;
//    @FXML
//    private TableColumn<PersonDto, String> emailCol;
//    @FXML
//    private TableColumn<PersonDto, String> uniEmailCol;
//    @FXML
//    private TableColumn<PersonDto, String> albumCol;
//    @FXML
//    private TableColumn<PersonDto, String> phoneNumberCol;
//    @FXML
//    private TableColumn<PersonDto, String> peselCol;
//    @FXML
//    private TableColumn<PersonDto, String> roleCol;
//    @FXML
//    private TableColumn<PersonDto, Boolean> enableCol;
    @FXML
    private VBox items;
    private ObservableList<PersonDto> obs = FXCollections.observableArrayList(
            new PersonDto("Jack", "Snow", "mail@mail.com",
                    "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
            new PersonDto("Jack", "Snow", "mail@mail.com",
                    "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", false),
            new PersonDto("Jack", "Snow", "mail@mail.com",
                    "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
            new PersonDto("Jack", "Snow", "mail@mail.com",
                    "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
            new PersonDto("Jack", "Snow", "mail@mail.com",
                    "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true)
    );

    public void initialize() {
        totalUsers.setText(String.valueOf(obs.toArray().length));
        activeAccounts.setText(String.valueOf(countActiveAccounts(obs)));
        loadUsersNodes(obs);

//        firstNameCol = new TableColumn<>("First Name");
//        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//        lastNameCol = new TableColumn<>("Last Name");
//        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//        emailCol = new TableColumn<>("Email");
//        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
//        uniEmailCol = new TableColumn<>("University Email");
//        uniEmailCol.setCellValueFactory(new PropertyValueFactory<>("universityEmail"));
//        albumCol = new TableColumn<>("Album");
//        albumCol.setCellValueFactory(new PropertyValueFactory<>("albumId"));
//        phoneNumberCol = new TableColumn<>("Phone Number");
//        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
//        peselCol = new TableColumn<>("Pesel");
//        peselCol.setCellValueFactory(new PropertyValueFactory<>("pesel"));
//        roleCol = new TableColumn<>("Role");
//        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
//        enableCol = new TableColumn<>("Enable");
//        enableCol.setCellValueFactory(new PropertyValueFactory<>("isEnable"));
//
//        people.getColumns().addAll(firstNameCol, lastNameCol, emailCol, uniEmailCol, albumCol, phoneNumberCol, peselCol, roleCol, enableCol);
//        people.setItems(obs);
    }

    private void loadUsersNodes(ObservableList<PersonDto> list) {
        Node[] nodes = new Node[list.toArray().length];
        for (int i = 0; i < list.toArray().length; i++) {
            try {
                String path = new File("").getAbsolutePath();
                URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);
                HBox v = (HBox) nodes[i];
                Label firstName = (Label) v.getChildren().get(0);
                Label secondName = (Label) v.getChildren().get(1);
                Label email = (Label) v.getChildren().get(2);
                Label albumId = (Label) v.getChildren().get(3);
                Label isActive = (Label) v.getChildren().get(4);
                firstName.setText(list.get(i).getFirstName());
                secondName.setText(list.get(i).getLastName());
                email.setText(list.get(i).getEmail());
                albumId.setText(list.get(i).getAlbumId());
                isActive.setText(list.get(i).isEnableProperty().getName());

                items.getChildren().addAll(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void loadLecturesNodes(ObservableList<PersonDto> list) {//todo: dokonczyc to
        Node[] nodes = new Node[list.toArray().length];
        for (int i = 0; i < list.toArray().length; i++) {
            try {
                String path = new File("").getAbsolutePath();
                URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);
                HBox v = (HBox) nodes[i];
                // lectures

                items.getChildren().addAll(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int countActiveAccounts(ObservableList<PersonDto> list) {
        int counter = 0;
        for(PersonDto personDto : list) {
            if(personDto.isIsEnable()) {
                counter++;
            }
        }

        return counter;
    }

    public void showStudents() {
        // load all students from db
        ObservableList<PersonDto> p = FXCollections.observableArrayList(
                new PersonDto("John", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Mike", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", false)
        );
        totalUsers.setText(String.valueOf(p.toArray().length));
        activeAccounts.setText(String.valueOf(countActiveAccounts(p)));
        items.getChildren().clear();
        loadUsersNodes(p);
    }

    public void showProfessors() {
        // load all professors from db
        ObservableList<PersonDto> p = FXCollections.observableArrayList(
                new PersonDto("Joe", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true),
                new PersonDto("Silvester", "Snow", "mail@mail.com",
                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true)
        );
        totalUsers.setText(String.valueOf(p.toArray().length));
        activeAccounts.setText(String.valueOf(countActiveAccounts(p)));
        items.getChildren().clear();
        loadUsersNodes(p);
    }

    public void showAddingForm(ActionEvent event) {
        Parent root;
        try {
            String path = new File("").getAbsolutePath();
            URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/admin/add-student-form.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 725, 383));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeWindow() {
//        System.out.println("ESSA");
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();
    }


    public void showStudentsList() {
        // temoprary symulate students list:
//        ObservableList<PersonDto> st = FXCollections.observableArrayList(
//                new PersonDto("Mark", "Essa", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "USER", true)
//        );
//
//        people.setItems(st);
    }
}
