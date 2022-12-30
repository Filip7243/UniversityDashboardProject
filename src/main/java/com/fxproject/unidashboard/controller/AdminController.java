package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.LectureDto;
import com.fxproject.unidashboard.dto.PersonDto;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.LectureRepository;
import com.fxproject.unidashboard.repository.PersonRepository;
import com.fxproject.unidashboard.repository.ProfessorRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.HibernateConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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
import java.util.stream.Stream;

import static com.fxproject.unidashboard.mapper.LectureMapper.mapToLectureDtos;
import static com.fxproject.unidashboard.mapper.PersonMapper.mapToPersonDtos;

public class AdminController {
    @FXML
    private Button closeButton;
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


    private PersonRepository pr = new PersonRepository();
    private StudentRepository sr = new StudentRepository();
    private ProfessorRepository profR = new ProfessorRepository();
    private LectureRepository lr = new LectureRepository();

    public void initialize() {
        try (Session session = HibernateConnect.openSession()) {
            Transaction transaction = session.beginTransaction();

            Addresses addresses = new Addresses(
                    null, "Poland", "Cracow", "Street", 24, 12, "21-133"
            );
            session.persist(addresses);
            Professors p = new Professors(null, "John", "Doe", "john@mail.com", "+48664812312", LocalDateTime.now(),
                    "New York", "01994337281", 'M', 22, addresses, AcademicTitles.MASTER);
            p.setAlbumId(12345678L);
            session.persist(p);
            Departments department = new Departments(null, "IT DEPARTMENT");
            session.persist(department);
            FieldsOfStudy fieldOfStudy = new FieldsOfStudy(
                    null, "IT", TypesOfStudy.ENGINEERING, department
            );
            FieldsOfStudy fieldOfStudy2 = new FieldsOfStudy(
                    null, "MATH", TypesOfStudy.BACHELOR, department
            );
            session.persist(fieldOfStudy);
            session.persist(fieldOfStudy2);
            Groups g = new Groups(null, "Lab2 - IT", fieldOfStudy);
            Groups g2 = new Groups(null, "Lab1 - MATH", fieldOfStudy2);
            Groups g3 = new Groups(null, "Lab1 - IT", fieldOfStudy);
            session.persist(g);
            session.persist(g2);
            session.persist(g3);

            Students s = new Students(null, "Mike", "Markulla", "mike@mail.com", "+48726513912", LocalDateTime.now(), "New York", "82777331923",
                    'M', 22, addresses);
            Students s1 = new Students(null, "Mikdsadase", "Markdsadasulla", "mcxzike@mail.com", "+48726543213912", LocalDateTime.now(), "Ndasvcxk", "123654756",
                    'F', 99, addresses);
            session.persist(s);
            session.persist(s1);

            Subjects subjects = new Subjects(null, "Algebra");
            Subjects subjects2 = new Subjects(null, "Analiza Matematyczna");
            session.persist(subjects);
            session.persist(subjects2);
            s.getGroups().add(g);
            s.getGroups().add(g2);

            ProfessorsSubjectsInGroups professorsSubjectsInGroups = new ProfessorsSubjectsInGroups(
                    p, subjects, g
            );
            session.persist(professorsSubjectsInGroups);

            UniversityAccounts acc = new UniversityAccounts("dsadas@mail.com", "jhadsgjhdsa", LocalDateTime.now(), true, s, Roles.ROLE_STUDENT);
            UniversityAccounts acc2 = new UniversityAccounts("dsadas@mail.com", "jhadsgjhdsa", LocalDateTime.now(), false, p, Roles.ROLE_PROFESSOR);
            UniversityAccounts acc3 = new UniversityAccounts("dfgfdgdf@mail.com", "dacxzx", LocalDateTime.now(), true, s1, Roles.ROLE_STUDENT);
            session.persist(acc);
            session.persist(acc2);
            session.persist(acc3);

            Lectures l = new Lectures(null, "DUPA", LocalDateTime.now(), g, subjects);
            Lectures l2 = new Lectures(null, "SRAKA", LocalDateTime.now(), g, subjects);
            session.persist(l);
            session.persist(l2);

            Marks m = new Marks(null, 3.5, LocalDateTime.now(), "ESSUNIA", s, subjects);
            Marks m1 = new Marks(null, 5.0, LocalDateTime.now(), "DCXZ", s, subjects);
            Marks m2 = new Marks(null, 4.5, LocalDateTime.now(), "dsa", s, subjects2);
            Marks m3 = new Marks(null, 2.5, LocalDateTime.now(), "vxSSS", s, subjects);
            Marks m4 = new Marks(null, 2.5, LocalDateTime.now(), "vxSSS", s1, subjects);
            Marks m5 = new Marks(null, 2.5, LocalDateTime.now(), "vxSSS", s1, subjects);
            Marks m6 = new Marks(null, 2.5, LocalDateTime.now(), "vxSSS", s1, subjects);
            Marks m7 = new Marks(null, 2.5, LocalDateTime.now(), "vxSSS", s1, subjects);
            session.persist(m);
            session.persist(m1);
            session.persist(m2);
            session.persist(m3);
            session.persist(m4);
            session.persist(m5);
            session.persist(m6);
            session.persist(m7);

            Wages w = new Wages(null, 2000.20, 200.1, 14.7, LocalDateTime.now(), p);
            session.persist(w);
            transaction.commit();
            System.out.println(profR.findProfessorByAlbumId(432L));
        }


    }

    private void loadUsersNodes(List<PersonDto> list) {
        Node[] nodes = new Node[list.size()];
        for (int i = 0; i < list.size(); i++) {
            try {
                String path = new File("").getAbsolutePath();
                URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/user-item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);
                HBox v = (HBox) nodes[i];
                v.setId("userItem" + i);
                PersonDto p = list.get(i);
                Label firstName = (Label) v.getChildren().get(0);
                Label lastName = (Label) v.getChildren().get(1);
                Label email = (Label) v.getChildren().get(2);
                Label albumId = (Label) v.getChildren().get(3);
                Label role = (Label) v.getChildren().get(4);
                Label isActive = (Label) v.getChildren().get(5);
                Button modifyBtn = (Button) v.getChildren().get(7);
                Button deleteBtn = (Button) v.getChildren().get(8);
                Button detailsBtn = (Button) v.getChildren().get(9);
                modifyBtn.setId("modifyButton" + i);
                deleteBtn.setId("deleteButton" + i);
                detailsBtn.setId("detailsButton" + i);
                firstName.setText(p.getFirstName());
                lastName.setText(p.getLastName());
                email.setText(p.getEmail());
                albumId.setText(String.valueOf(p.getAlbumId()));
                role.setText(p.getRole().substring(5));
                isActive.setText(String.valueOf(p.isActive()));
                itemsContainer.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLecturesNodes(List<LectureDto> list) {
        Node[] nodes = new Node[10];
        for (int i = 0; i < list.size(); i++) {
            try {
                String path = new File("").getAbsolutePath();
                URL url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/lecture-item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);
                HBox v = (HBox) nodes[i];
                BorderPane idPane = (BorderPane) v.getChildren().get(0);
                BorderPane topicPan = (BorderPane) v.getChildren().get(1);
                BorderPane datePane = (BorderPane) v.getChildren().get(2);
                BorderPane groupPane = (BorderPane) v.getChildren().get(3);
                BorderPane subjectPane = (BorderPane) v.getChildren().get(4);
                HBox buttonsContainer = (HBox) v.getChildren().get(6);
                Button deleteButton = (Button) buttonsContainer.getChildren().get(0);
                Button modifyButton = (Button) buttonsContainer.getChildren().get(1);
                deleteButton.setId("deleteButton" + i);
                modifyButton.setId("modifyButton" + i);
                v.setPrefWidth(800.0);
                v.setId("lectureItem" + i);
                // lectures
                LectureDto lectureDto = list.get(i);
                Label id = (Label) idPane.getChildren().get(0);
                Label topic = (Label) topicPan.getChildren().get(0);
                Label date = (Label) datePane.getChildren().get(0);
                Label group = (Label) groupPane.getChildren().get(0);
                Label subject = (Label) subjectPane.getChildren().get(0);
                id.setText(String.valueOf(lectureDto.getId()));
                topic.setText(lectureDto.getTopic());
                date.setText(lectureDto.getDate().toString());
                group.setText(lectureDto.getGroupName());
                subject.setText(lectureDto.getSubjectName());

                itemsContainer.getChildren().addAll(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int countActiveAccounts(List<PersonDto> list) {
        int counter = 0;
        for (PersonDto personDto : list) {
            if (personDto.isActive()) {
                counter++;
            }
        }

        return counter;
    }

    public void showStudents() {
        // load all students from db
        List<Students> allStudents = sr.findAllStudents();
        loadDtos(mapToPersonDtos(allStudents));
    }

    public void showProfessors() {
        // load all professors from db
        List<Professors> allProfessors = profR.findAllProfessors();
        loadDtos(mapToPersonDtos(allProfessors));
    }

    private void loadDtos(List<PersonDto> personDtos) {
        totalUsers.setText(String.valueOf(personDtos.size()));
        activeAccounts.setText(String.valueOf(countActiveAccounts(personDtos)));
        totalLabel.setText("Total Users");
        activeLabel.setText("Active Accounts");
        itemsContainer.getChildren().clear();
        loadUsersNodes(personDtos);
    }

    public void showLectures() {
        List<Lectures> allLectures = lr.findAllLectures();
        List<LectureDto> lectureDtos = mapToLectureDtos(allLectures);
        totalUsers.setText(String.valueOf(lectureDtos.size())); // total lectures
        activeAccounts.setText("7"); // todo: method that checks "alive" lessons
        totalLabel.setText("All lectures");
        activeLabel.setText("Now Lessons");
        itemsContainer.getChildren().clear();
        loadLecturesNodes(lectureDtos);
    }


    public void showAddingForm(ActionEvent event) {
        Parent root;
        try {
            String path = new File("").getAbsolutePath();
            Button btn = (Button) event.getSource();
            URL url = null;
            switch (btn.getId()) {
                case "addProfessorButton" ->
                        url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/admin/add-professor-form.fxml").toURI().toURL();
                case "addStudentButton" ->
                        url = new File(path + "/src/main/resources/com/fxproject/unidashboard/fxml/admin/add-student-form.fxml").toURI().toURL();
            }

            if (url != null) {
                root = FXMLLoader.load(url);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root, 725, 383));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = ((Stage) event.getSource());
        stage.close();
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
