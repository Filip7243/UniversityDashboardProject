package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.FieldOfStudyGroupDto;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.WageRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ProfessorDetailsController {
    @FXML
    private Accordion details;
    @FXML
    private TitledPane professorData;
    @FXML
    private TitledPane personalData;
    @FXML
    private TitledPane accountData;
    @FXML
    private TitledPane addressData;

    private WageRepository wg = new WageRepository();

    public void loadProfessorData() {
        Professors userData = getDataFromStage();
        List<ProfessorsSubjectsInGroups> psig = userData.getPsig();
        List<FieldOfStudyGroupDto> fieldOfStudyGroupDtos = psig.stream().
                map(e -> {
                    Groups group = e.getGroup();
                    return new FieldOfStudyGroupDto(group.getFieldOfStudy().getName(), group.getName());
                }).toList();
        List<Wages> professorWages = wg.findProfessorWages(userData);

        VBox content = (VBox) professorData.getContent();
        ObservableList<Node> children = content.getChildren();
        TableView<FieldOfStudyGroupDto> fieldOfStudyGroup = (TableView<FieldOfStudyGroupDto>) children.get(0);
        fieldOfStudyGroup.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("filedOfStudyName"));
        fieldOfStudyGroup.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("groupName"));
        fieldOfStudyGroup.setItems(FXCollections.observableArrayList(fieldOfStudyGroupDtos));
        TableView<Wages> wages = (TableView<Wages>) children.get(1);
        wages.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("salary"));
        wages.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("hourlyRate"));
        wages.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("hoursWorked"));
        wages.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("payday"));
        wages.setItems(FXCollections.observableArrayList(professorWages));
    }

    private Professors getDataFromStage() {
        Stage stage = (Stage) details.getScene().getWindow();
        return (Professors) stage.getUserData();
    }

    public void loadPersonalData() {
        Professors userData = getDataFromStage();
        // get personal data from db
        VBox content = (VBox) personalData.getContent();
        ObservableList<Node> children = content.getChildren();
        for (Node child : children) {
            // child is hbox
            HBox i = (HBox) child;
            Label label = (Label) i.getChildren().get(0);
            switch (label.getText()) {
                case "First Name" -> {
                    Label firstName = (Label) i.getChildren().get(1);
                    firstName.setText(userData.getFirstName());
                }
                case "Last Name" -> {
                    Label secondName = (Label) i.getChildren().get(1);
                    secondName.setText(userData.getLastName());
                }
                case "Email" -> {
                    Label email = (Label) i.getChildren().get(1);
                    email.setText(userData.getEmail());
                }
                case "Phone Number" -> {
                    Label phoneNumber = (Label) i.getChildren().get(1);
                    phoneNumber.setText(userData.getPhoneNumber());
                }
                case "Date Of Birth" -> {
                    Label dateOfBirth = (Label) i.getChildren().get(1);
                    dateOfBirth.setText(String.valueOf(userData.getBirthday()));
                }
                case "Place Of Birth" -> {
                    Label placeOfBirth = (Label) i.getChildren().get(1);
                    placeOfBirth.setText(String.valueOf(userData.getPlaceOfBirth()));
                }
                case "Gender" -> {
                    Label gender = (Label) i.getChildren().get(1);
                    gender.setText(String.valueOf(userData.getGender()));
                }
                case "Age" -> {
                    Label age = (Label) i.getChildren().get(1);
                    age.setText(String.valueOf(userData.getAge()));
                }
            }

        }
    }

    public void loadAccountData() {
        Professors userData = getDataFromStage();
        UniversityAccounts acc = userData.getAcc();
        VBox content = (VBox) accountData.getContent();
        ObservableList<Node> children = content.getChildren();
        for (Node child : children) {
            HBox i = (HBox) child;
            Label label = (Label) i.getChildren().get(0);
            switch (label.getText()) {
                case "University Email" -> ((Label) i.getChildren().get(1)).setText(acc.getUniversityEmail());
                case "Created At" -> ((Label) i.getChildren().get(1)).setText(String.valueOf(acc.getCreatedAt()));
                case "Active" -> ((Label) i.getChildren().get(1)).setText(String.valueOf(acc.getEnabled()));
                case "Role" -> ((Label) i.getChildren().get(1)).setText(acc.getRole().name().substring(5));
            }
        }
    }

    public void loadAddressData() {
        Professors userData = getDataFromStage();
        Addresses address = userData.getAddress();
        VBox content = (VBox) addressData.getContent();
        ObservableList<Node> children = content.getChildren();
        for (Node child : children) {
            HBox i = (HBox) child;
            Label label = (Label) i.getChildren().get(0);
            final Label infoLabel = (Label) i.getChildren().get(1);
            switch (label.getText()) {
                case "Country" -> infoLabel.setText(address.getCountry());
                case "City" -> infoLabel.setText(address.getCity());
                case "Street" -> infoLabel.setText(address.getStreet());
                case "House Number" -> infoLabel.setText(String.valueOf(address.getHouseNumber()));
                case "Flat Number" -> infoLabel.setText(String.valueOf(address.getFlatNumber()));
                case "Postal Code" -> infoLabel.setText(address.getPostalCode());
            }
        }
    }

    public void closeWindow(ActionEvent event) {
        ((Stage) ((Button) (event.getSource())).getScene().getWindow()).close();
    }
}
