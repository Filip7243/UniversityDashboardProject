package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.PersonDto;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.PersonRepository;
import com.fxproject.unidashboard.utils.HibernateConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

public class AdminController {
    @FXML
    private Label activeLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private Label totalUsers;
    @FXML
    private Label activeAccounts;
    @FXML
    private VBox itemsContainer; // contains students/professors/lectures etc.

    private ObservableList<PersonDto> users;
    private ObservableList<?> lectures; // ? = lectureDTO;

    private PersonRepository pr = new PersonRepository();

    public void initialize() {
        try (Session session = HibernateConnect.openSession()) {
            Transaction transaction = session.beginTransaction();

            Addresses addresses = new Addresses(
                    null, "Poland", "Cracow", "Street", 24, 12, "21-133"
            );
            session.persist(addresses);
            Professors p = new Professors(null, "John", "Doe", "john@mail.com", "+48664812312", LocalDateTime.now(),
                    "New York", "01994337281", 'M', 22, addresses, AcademicTitles.MASTER);
            p.setAlbumId("12345678");
            session.persist(p);
            Departments department = new Departments(null, "IT DEPARTMENT");
            session.persist(department);
            FieldsOfStudy fieldOfStudy = new FieldsOfStudy(
                    null, "IT", TypesOfStudy.ENGINEERING, department
            );
            session.persist(fieldOfStudy);
            Groups g = new Groups(null, "Lab2 - IT", fieldOfStudy);
            session.persist(g);
            Students s = new Students(null, "Mike", "Markulla", "mike@mail.com", "+48726513912", LocalDateTime.now(), "New York", "82777331923",
                    'M', 22, addresses);
            session.persist(s);

            Subjects subjects = new Subjects(null, "Algebra");
            session.persist(subjects);
            s.getGroups().add(g);

            ProfessorsSubjectsInGroups professorsSubjectsInGroups = new ProfessorsSubjectsInGroups(
                    p, subjects, g
            );
            session.persist(professorsSubjectsInGroups);

            UniversityAccounts acc = new UniversityAccounts("dsadas@mail.com", "jhadsgjhdsa", LocalDateTime.now(), true, s, Roles.ROLE_STUDENT);
            UniversityAccounts acc2 = new UniversityAccounts("dsadas@mail.com", "jhadsgjhdsa", LocalDateTime.now(), true, p, Roles.ROLE_STUDENT);
            session.persist(acc);
            session.persist(acc2);
            transaction.commit();
        }


    }

    private void loadUsersNodes(ObservableList<Students> list) {
        // load users from db
        // map to PersonDTO
        Node[] nodes = new Node[list.toArray().length];
        for (int i = 0; i < list.toArray().length; i++) {
            try {
                String path = new File("").getAbsolutePath();
                URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/user-item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);
                HBox v = (HBox) nodes[i];
                v.setId("userItem");
                Label firstName = (Label) v.getChildren().get(0);
                Label lastName = (Label) v.getChildren().get(1);
                Label email = (Label) v.getChildren().get(2);
                Label albumId = (Label) v.getChildren().get(3);
                Label role = (Label) v.getChildren().get(4);
                Label isActive = (Label) v.getChildren().get(5);
                firstName.setText(list.get(i).getFirstName());
                lastName.setText(list.get(i).getLastName());
                email.setText(list.get(i).getEmail());
                albumId.setText(String.valueOf(list.get(i).getAlbumId()));
//                role.setText(list.get(i).getRole());
//                isActive.setText(String.valueOf(list.get(i).isIsEnable()));
                itemsContainer.getChildren().addAll(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLecturesNodes() {//todo: dokonczyc to, zmienic na lectures
        Node[] nodes = new Node[10];
        for (int i = 0; i < 10; i++) {
            try {
                String path = new File("").getAbsolutePath();
                URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/lecture-item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);
                HBox v = (HBox) nodes[i];
                v.getChildren().remove(6); // modify button
                v.setPrefWidth(720.0);
                v.setId("lectureItem");
                // lectures
                Label topic = (Label) v.getChildren().get(0);
                Label date = (Label) v.getChildren().get(1);
                Label group = (Label) v.getChildren().get(2);
                Label subject = (Label) v.getChildren().get(3);
                Label classroom = (Label) v.getChildren().get(4);
                topic.setText("Topic of the lecture");
                date.setText("20-02-2001");
                group.setText("Informatyka II, Lab2");
                subject.setText("Programming");
                classroom.setText("204");

                itemsContainer.getChildren().addAll(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int countActiveAccounts(ObservableList<PersonDto> list) {
        int counter = 0;
        for (PersonDto personDto : list) {
//            if(personDto.isIsEnable()) {
//                counter++;
//            }
        }

        return counter;
    }

    public void showStudents() {
        // load all students from db
        List<Person> allPeople = pr.findAllPeople();
//        totalUsers.setText(String.valueOf(p.toArray().length));
//        activeAccounts.setText(String.valueOf(countActiveAccounts(p)));
        totalLabel.setText("Total Users");
        activeLabel.setText("Active Accounts");
        itemsContainer.getChildren().clear();
//        loadUsersNodes(p);
    }

    public void showProfessors() {
        // load all professors from db
        ObservableList<PersonDto> p = FXCollections.observableArrayList(
//                new PersonDto("Joe", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true),
//                new PersonDto("Silvester", "Snow", "mail@mail.com",
//                        "uni@mail.com", "122311", "+48 663-281-492", "01277449281", "Professor", true)
        );
        totalUsers.setText(String.valueOf(p.toArray().length));
        activeAccounts.setText(String.valueOf(countActiveAccounts(p)));
        totalLabel.setText("Total Users");
        activeLabel.setText("Active Accounts");
        itemsContainer.getChildren().clear();
//        loadUsersNodes(p);
    }

    public void showLectures() {
        System.out.println("dupa");
        totalUsers.setText("10"); // total lectures
        activeAccounts.setText("7"); // 7 done 3 in progress right now todo: method
        totalLabel.setText("All lectures");
        activeLabel.setText("Now Lessons");
        itemsContainer.getChildren().clear();
        loadLecturesNodes();
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
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
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
