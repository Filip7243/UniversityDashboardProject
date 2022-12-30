package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.AccountRepository;
import com.fxproject.unidashboard.repository.AddressRepository;
import com.fxproject.unidashboard.repository.PersonRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AddFormController {

    @FXML
    private DatePicker birthday;

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
    private ComboBox<String> gender;

    @FXML
    private TextField houseNumber;

    @FXML
    private TextField lastName;

    @FXML
    private TextField pesel;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField placeBirth;

    @FXML
    private TextField postalCode;

    @FXML
    private TextField secondName;
    @FXML
    private ComboBox<AcademicTitles> academicTitleCombo;

    @FXML
    private TextField street;

    private AddressRepository ar = new AddressRepository();
    private PersonRepository pr = new PersonRepository();
    private AccountRepository accr = new AccountRepository();

    public void initialize() {
        gender.setItems(FXCollections.observableArrayList(List.of("Male", "Female")));
        if(academicTitleCombo != null) {
            academicTitleCombo.setItems(
                    FXCollections.observableArrayList(List.of(AcademicTitles.DOCTOR, AcademicTitles.MASTER, AcademicTitles.ENGINEER))
            );
        }
    }
    public void addUser() {
        System.out.println();
        Person person = new Person();
        person.setFirstName(firstName.getText());
        person.setLastName(lastName.getText());
        person.setEmail(email.getText());
        person.setBirthday(birthday.getValue().atTime(LocalTime.now()));
        person.setPlaceOfBirth(placeBirth.getText());
        person.setPesel(pesel.getText());
        person.setPhoneNumber(phoneNumber.getText());
        person.setGender(gender.getValue().charAt(0));
        person.setAge(LocalDateTime.now().getYear() - person.getBirthday().getYear());
        Addresses address = new Addresses();
        address.setCountry(country.getText());
        address.setCity(city.getText());
        address.setPostalCode(postalCode.getText());
        address.setStreet(street.getText());
        address.setHouseNumber(Integer.parseInt(houseNumber.getText()));
        address.setFlatNumber(Integer.parseInt(flatNumber.getText()));
        person.setAddress(address);
        ar.save(address);
        pr.save(person);
        UniversityAccounts acc = new UniversityAccounts();
        acc.setUniversityEmail("dsab@unimail.com"); //todo: email generator
        acc.setPassword("khdsajdgaJSDAHMC6713SAJDHnjskadn"); //todo: password generator
        acc.setCreatedAt(LocalDateTime.now());
        acc.setEnabled(false);
        acc.setPerson(person);
        if(academicTitleCombo != null) { // it means we are adding professor
            acc.setRole(Roles.ROLE_PROFESSOR);
        } else {
            acc.setRole(Roles.ROLE_STUDENT);
        }
        accr.save(acc);
        person.setAcc(acc);
    }

    public void cancelAdding() {
        System.out.println("ESSUNIA");
    }
}
