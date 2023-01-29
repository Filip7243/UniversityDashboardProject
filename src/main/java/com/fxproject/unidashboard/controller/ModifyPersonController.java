package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.Addresses;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.Roles;
import com.fxproject.unidashboard.model.UniversityAccounts;
import com.fxproject.unidashboard.repository.AccountRepository;
import com.fxproject.unidashboard.repository.PersonRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;

import static com.fxproject.unidashboard.validator.Validator.*;

public class ModifyPersonController {
    @FXML
    private VBox accountDataVbox;

    @FXML
    private TextField city;

    @FXML
    private TextField country;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField flatNumber;

    @FXML
    private TextField gender;

    @FXML
    private TextField lastName;

    @FXML
    private TitledPane personalData;

    @FXML
    private TitledPane accData;

    @FXML
    private VBox personalDataVbox;

    @FXML
    private AnchorPane personalDetails;

    @FXML
    private TextField pesel;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField placeOfBirth;

    @FXML
    private TextField postalCode;

    @FXML
    private TextField street;

    @FXML
    private TextField universityEmail;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<Roles> role;

    private PersonRepository pr = new PersonRepository();
    private AccountRepository ar = new AccountRepository();


    public void initialize() {
        // personal data
        setValidator(firstName, stringValidator());
        setValidator(lastName, stringValidator());
        setValidator(placeOfBirth, stringValidator());
        setValidator(pesel, integerValidator());
        setValidator(phoneNumber, integerValidator());
        setValidator(gender, stringValidator());
        setValidator(country, stringValidator());
        setValidator(city, stringValidator());
        setValidator(street, stringValidator());
        setValidator(flatNumber, integerValidator());


        emailValidator(email);
        emailValidator(universityEmail);
        lengthValidator(pesel, 11);
        lengthValidator(phoneNumber, 9);
        lengthValidator(postalCode, 6);
        lengthValidator(gender, 1);
        role.setItems(FXCollections.observableArrayList(Roles.values()));
        role.setDisable(true);
    }

    public void loadPersonalData() {
        Person userData = getPersonDataFromStage();
        Addresses address = userData.getAddress();
        ObservableList<Node> children = personalDataVbox.getChildren();
        for (int i = 1; i < children.size() - 1; i++) {
            HBox child = (HBox) children.get(i);
            Pane pane = (Pane) child.getChildren().get(0);
            Label label = (Label) pane.getChildren().get(0);
            switch (label.getText()) {
                case "First Name" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField firstName = (TextField) p.getChildren().get(0);
                    firstName.setText(userData.getFirstName());
                }
                case "Last Name" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField lastName = (TextField) p.getChildren().get(0);
                    lastName.setText(userData.getLastName());
                }
                case "Email" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField email = (TextField) p.getChildren().get(0);
                    email.setText(userData.getEmail());
                }
                case "Phone Number" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField phoneNumber = (TextField) p.getChildren().get(0);
                    phoneNumber.setText(userData.getPhoneNumber());
                }
                case "Date Of Birth" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField dateOfBirth = (TextField) p.getChildren().get(0);
                    dateOfBirth.setText(String.valueOf(userData.getBirthday()));
                }
                case "Place Of Birth" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField placeOfBirth = (TextField) p.getChildren().get(0);
                    placeOfBirth.setText(String.valueOf(userData.getPlaceOfBirth()));
                }
                case "Gender" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField gender = (TextField) p.getChildren().get(0);
                    gender.setText(String.valueOf(userData.getGender()));
                }
                case "Age" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField age = (TextField) p.getChildren().get(0);
                    age.setText(String.valueOf(userData.getAge()));
                }
                case "Country" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField infoLabel = (TextField) p.getChildren().get(0);
                    infoLabel.setText(address.getCountry());
                }
                case "City" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField infoLabel = (TextField) p.getChildren().get(0);
                    infoLabel.setText(address.getCity());
                }
                case "Street" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField infoLabel = (TextField) p.getChildren().get(0);
                    infoLabel.setText(address.getStreet());
                }
                case "Pesel" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField pesel = (TextField) p.getChildren().get(0);
                    pesel.setText(userData.getPesel());
                }
                case "House Number" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField infoLabel = (TextField) p.getChildren().get(0);
                    infoLabel.setText(String.valueOf(address.getHouseNumber()));
                }
                case "Flat Number" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField infoLabel = (TextField) p.getChildren().get(0);
                    infoLabel.setText(String.valueOf(address.getFlatNumber()));
                }
                case "Postal Code" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField infoLabel = (TextField) p.getChildren().get(0);
                    infoLabel.setText(address.getPostalCode());
                }
            }
        }
    }

    public void loadAccountData() {
        Person userData = getPersonDataFromStage();
        UniversityAccounts acc = userData.getAcc();
        ObservableList<Node> children = accountDataVbox.getChildren();
        for (int i = 1; i < children.size() - 1; i++) {
            HBox child = (HBox) children.get(i);
            Pane pane = (Pane) child.getChildren().get(0);
            Label label = (Label) pane.getChildren().get(0);
            switch (label.getText()) {
                case "University Email" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField infoLabel = (TextField) p.getChildren().get(0);
                    infoLabel.setText(acc.getUniversityEmail());
                }
                case "Password" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    TextField infoLabel = (TextField) p.getChildren().get(0);
                    infoLabel.setText(acc.getPassword());
                }
                case "Role" -> {
                    Pane p = (Pane) child.getChildren().get(1);
                    ComboBox<Roles> roleCombo = (ComboBox<Roles>) p.getChildren().get(0);
                    roleCombo.getSelectionModel().select(acc.getRole());
                }
            }
        }
    }

    private Person getPersonDataFromStage() {
        Stage stage = (Stage) personalDetails.getScene().getWindow();
        Person userData = (Person) stage.getUserData();
        return pr.findPersonWithId(userData.getId()).orElseThrow();
    }

    public void modifyPersonalData(ActionEvent event) {
        if (validatePersonalData()) {
            Person userData = getPersonDataFromStage();
            pr.updatePersonWithId(new Person(userData.getId(), firstName.getText(), lastName.getText(), email.getText(), phoneNumber.getText(),
                    LocalDateTime.now(), placeOfBirth.getText(), pesel.getText(), gender.getText().charAt(0), 99,
                    new Addresses(userData.getAddress().getId(), country.getText(), city.getText(),
                            street.getText(), 12, Integer.parseInt(flatNumber.getText()), postalCode.getText())
            ), userData.getId());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "User Modified");
            a.show();
            ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Can't modify! Try again.");
            a.show();
        }
    }

    public void modifyAccountData(ActionEvent event) {
        if (validateAccData()) {
            Person userData = getPersonDataFromStage();
            UniversityAccounts acc = userData.getAcc();
            UniversityAccounts updatedAcc = new UniversityAccounts(
                    universityEmail.getText(), password.getText(), acc.getCreatedAt(),
                    acc.getEnabled(), acc.getPerson(), acc.getRole());
            if(ar.findAccWithEmail(updatedAcc.getUniversityEmail()).isPresent()) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("User with this email already exists! Try again");
                a.show();
                return;
            }
            ar.updateAccountWithPersonId(updatedAcc, userData.getId());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "User Account Modified");
            a.show();
            ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Can't modify! Try again.");
            a.show();
        }

    }

    public void cancelAccountTab() {
        accData.expandedProperty().set(false);
    }

    public void cancelPersonalDataTab() {
        personalData.expandedProperty().set(false);
    }

    private boolean validatePersonalData() {
        return checkIfEmpty(List.of(firstName, lastName, email, placeOfBirth, pesel, phoneNumber, gender,
                country, city, postalCode, street, flatNumber));
    }

    private boolean validateAccData() {
        return checkIfEmpty(List.of(universityEmail, password));
    }
}
