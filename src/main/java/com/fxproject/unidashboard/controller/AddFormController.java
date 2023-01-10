package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.email.EmailAddressGenerator;
import com.fxproject.unidashboard.email.PasswordGenerator;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.AccountRepository;
import com.fxproject.unidashboard.repository.AddressRepository;
import com.fxproject.unidashboard.repository.ProfessorRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private AccountRepository accr = new AccountRepository();
    private StudentRepository sr = new StudentRepository();
    private ProfessorRepository professorRepository = new ProfessorRepository();

    public void initialize() {
        gender.setItems(FXCollections.observableArrayList(List.of("Male", "Female")));
        if (academicTitleCombo != null) {
            academicTitleCombo.setItems(
                    FXCollections.observableArrayList(List.of(AcademicTitles.DOCTOR, AcademicTitles.MASTER, AcademicTitles.ENGINEER))
            );
        }
    }

    public void addUser() {
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

        UniversityAccounts acc = new UniversityAccounts();
        Addresses address = new Addresses();
        if (academicTitleCombo != null) { // it means we are adding professor
            address.setCountry(country.getText());
            address.setCity(city.getText());
            address.setPostalCode(postalCode.getText());
            address.setStreet(street.getText());
            address.setHouseNumber(Integer.parseInt(houseNumber.getText()));
            address.setFlatNumber(Integer.parseInt(flatNumber.getText()));
            person.setAddress(address);
            ar.save(address);
            Professors p = new Professors(null, person.getFirstName(), person.getLastName(), person.getEmail(),
                    person.getPhoneNumber(), person.getBirthday(), person.getPlaceOfBirth(), person.getPesel(),
                    person.getGender(), person.getAge(), person.getAddress(), academicTitleCombo.getValue());
            professorRepository.save(p);
            acc.setRole(Roles.ROLE_PROFESSOR);
            acc.setUniversityEmail(EmailAddressGenerator.generateMailForProfessor(p));
            acc.setPassword(PasswordGenerator.generatePassword());
            acc.setCreatedAt(LocalDateTime.now());
            acc.setEnabled(false);
            acc.setPerson(p);
            accr.save(acc);
            p.setAcc(acc);
        } else {
            address.setCountry(country.getText());
            address.setCity(city.getText());
            address.setPostalCode(postalCode.getText());
            address.setStreet(street.getText());
            address.setHouseNumber(Integer.parseInt(houseNumber.getText()));
            address.setFlatNumber(Integer.parseInt(flatNumber.getText()));
            person.setAddress(address);
            ar.save(address);
            acc.setRole(Roles.ROLE_STUDENT);
            Students s = new Students(null, person.getFirstName(), person.getLastName(), person.getEmail(),
                    person.getPhoneNumber(), person.getBirthday(), person.getPlaceOfBirth(), person.getPesel(),
                    person.getGender(), person.getAge(), person.getAddress());
            sr.save(s);
            acc.setRole(Roles.ROLE_PROFESSOR);
            acc.setUniversityEmail(EmailAddressGenerator.generateMailForStudent(s));
            acc.setPassword(PasswordGenerator.generatePassword());
            acc.setCreatedAt(LocalDateTime.now());
            acc.setEnabled(false);
            acc.setPerson(s);
            accr.save(acc);
            s.setAcc(acc);
        }
    }

    public void closeWindow(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void cancelAdding(ActionEvent event) {
        closeWindow(event);
    }
}
