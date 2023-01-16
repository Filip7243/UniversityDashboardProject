package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.HelloApplication;
import com.fxproject.unidashboard.model.UniversityAccounts;
import com.fxproject.unidashboard.repository.AccountRepository;
import com.fxproject.unidashboard.schema.InsertData;
import com.fxproject.unidashboard.utils.HibernateConnect;
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
import org.hibernate.Session;

import java.io.IOException;

public class LoginController {

    @FXML
    private JFXButton closeButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    private AccountRepository ar = new AccountRepository();
    // dsadas@mail.com jhadsgjhdsa

    public void initialize() {
//        new InsertData();
//        Session session = HibernateConnect.openSession();
//        try (Session session = HibernateConnect.openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            Addresses addresses = new Addresses(
//                    null, "Poland", "Cracow", "Street", 24, 12, "21-133"
//            );
//            session.persist(addresses);
//            Professors p = new Professors(null, "John", "Doe", "john@mail.com", "+48664812312", LocalDateTime.now(),
//                    "New York", "01994337281", 'M', 22, addresses, AcademicTitles.MASTER);
//            Professors p1 = new Professors(null, "Hikari", "Doe", "john@mail.com", "+48664812312", LocalDateTime.now(),
//                    "New York", "53264837221", 'F', 54, addresses, AcademicTitles.DOCTOR);
//            p.setAlbumId(12345678L);
//            p1.setAlbumId(3278163L);
//            session.persist(p);
//            session.persist(p1);
//            Departments department = new Departments(null, "IT DEPARTMENT");
//            session.persist(department);
//            FieldsOfStudy fieldOfStudy = new FieldsOfStudy(
//                    null, "IT", TypesOfStudy.ENGINEERING, department
//            );
//            FieldsOfStudy fieldOfStudy2 = new FieldsOfStudy(
//                    null, "MATH", TypesOfStudy.BACHELOR, department
//            );
//            session.persist(fieldOfStudy);
//            session.persist(fieldOfStudy2);
//            Groups g = new Groups(null, "Lab2 - IT", fieldOfStudy);
//            Groups g2 = new Groups(null, "Lab1 - MATH", fieldOfStudy2);
//            Groups g3 = new Groups(null, "Lab1 - IT", fieldOfStudy);
//            session.persist(g);
//            session.persist(g2);
//            session.persist(g3);
//
//            Students s = new Students(null, "Mike", "Markulla", "mike@mail.com", "+48726513912", LocalDateTime.now(), "New York", "82777331923",
//                    'M', 22, addresses);
//            Students s1 = new Students(null, "Mikdsadase", "Markdsadasulla", "mcxzike@mail.com", "+48726543213912", LocalDateTime.now(), "Ndasvcxk", "123654756",
//                    'F', 99, addresses);
//            session.persist(s);
//            session.persist(s1);
//
//            Subjects subjects = new Subjects(null, "Algebra");
//            Subjects subjects2 = new Subjects(null, "Analiza Matematyczna");
//            session.persist(subjects);
//            session.persist(subjects2);
//            s.getGroups().add(g);
//            s.getGroups().add(g2);
//            s1.getGroups().add(g);
//
//            ProfessorsSubjectsInGroups professorsSubjectsInGroups = new ProfessorsSubjectsInGroups(
//                    p, subjects, g
//            );
//            ProfessorsSubjectsInGroups professorsSubjectsInGroupss = new ProfessorsSubjectsInGroups(
//                    p, subjects2, g
//            );
//            ProfessorsSubjectsInGroups abc = new ProfessorsSubjectsInGroups(
//                    p, subjects, g2
//            );
//            ProfessorsSubjectsInGroups abc1 = new ProfessorsSubjectsInGroups(
//                    p1, subjects, g2
//            );
//            session.persist(professorsSubjectsInGroups);
//            session.persist(professorsSubjectsInGroupss);
//            session.persist(abc);
//            session.persist(abc1);
//
//            UniversityAccounts acc = new UniversityAccounts("s", "1234", LocalDateTime.now(), true, s, Roles.ROLE_STUDENT);
//            UniversityAccounts acc2 = new UniversityAccounts("p", "1", LocalDateTime.now(), true, p, Roles.ROLE_PROFESSOR);
//            UniversityAccounts acc3 = new UniversityAccounts("p1", "1", LocalDateTime.now(), true, p1, Roles.ROLE_PROFESSOR);
//            UniversityAccounts acc4 = new UniversityAccounts("s1", "1", LocalDateTime.now(), true, s1, Roles.ROLE_STUDENT);
//            session.persist(acc);
//            session.persist(acc2);
//            session.persist(acc3);
//            session.persist(acc4);
//
//            Person admin = new Person(null, "dsadas", "dsacxz", "dsanca", "283916941", LocalDateTime.now(),
//                    "dsadas", "89371321", 'M', 62, null);
//            UniversityAccounts accAdmin = new UniversityAccounts("admin", "admin", LocalDateTime.now(), true, admin, Roles.ROLE_ADMIN);
//            session.persist(admin);
//            session.persist(accAdmin);
//
//            Lectures l = new Lectures(null, "Bardzo Fajny wykład", LocalDateTime.now(), g, subjects, p);
//            Lectures l2 = new Lectures(null, "Jak ugotować jajka w 5 minut", LocalDateTime.now(), g, subjects2, p);
//            Lectures l3 = new Lectures(null, "Jak ugotować jajka w 5 minut", LocalDateTime.now(), g, subjects2, p);
//            session.persist(l);
//            session.persist(l2);
//
//            Marks m = new Marks(null, 3.5, LocalDateTime.now(), "ESSUNIA", s, subjects, ExamTypes.TEST);
//            Marks m1 = new Marks(null, 5.0, LocalDateTime.now(), "DCXZ", s, subjects, ExamTypes.SEMESTER_EXAM);
//            Marks m2 = new Marks(null, 4.5, LocalDateTime.now(), "dsa", s, subjects2, ExamTypes.TEST);
//            Marks m3 = new Marks(null, 2.5, LocalDateTime.now(), "vxSSS", s, subjects, ExamTypes.TEST);
//            Marks m4 = new Marks(null, 2.5, LocalDateTime.now(), "vxSSS", s1, subjects, ExamTypes.TEST);
//            Marks m5 = new Marks(null, 2.5, LocalDateTime.now(), "vxSSS", s1, subjects, ExamTypes.TEST);
//            Marks m6 = new Marks(null, 2.5, LocalDateTime.now(), "vxSSS", s1, subjects, ExamTypes.TEST);
//            Marks m7 = new Marks(null, 2.5, LocalDateTime.now(), "vxSSS", s1, subjects, ExamTypes.TEST);
//            session.persist(m);
//            session.persist(m1);
//            session.persist(m2);
//            session.persist(m3);
//            session.persist(m4);
//            session.persist(m5);
//            session.persist(m6);
//            session.persist(m7);
//
//            Wages w = new Wages(null, 2000.20, 200.1, 14.7, LocalDate.now(), p);
//            session.persist(w);
//            Attendances a = new Attendances(null, true, false, l2, s);
//            Attendances a1 = new Attendances(null, true, false, l, s);
//            Attendances a2 = new Attendances(null, true, false, l, s);
//            session.persist(a);
//            session.persist(a1);
//            session.persist(a2);
//
//            Exams e = new Exams(null, "EGZAMIN", LocalDateTime.of(2022, 12, 21, 21, 37),
//                    ExamTypes.SEMESTER_EXAM, g, subjects);
//            Exams e1 = new Exams(null, "SESJA", LocalDateTime.of(2022, 11, 12, 21, 37),
//                    ExamTypes.SEMESTER_EXAM, g, subjects);
//            Exams e2 = new Exams(null, "KOLOS", LocalDateTime.of(2022, 7, 17, 21, 37),
//                    ExamTypes.TEST, g2, subjects);
//            Exams e3 = new Exams(null, "TEZ KOLOS", LocalDateTime.of(2023, 2, 11, 21, 37),
//                    ExamTypes.TEST, g2, subjects);
//            Exams e4 = new Exams(null, "TO JUZ SESJA", LocalDateTime.of(2023, 2, 11, 21, 37),
//                    ExamTypes.SEMESTER_EXAM, g2, subjects);
//
//            session.persist(e);
//            session.persist(e1);
//            session.persist(e2);
//            session.persist(e3);
//            session.persist(e4);
//            transaction.commit();
//
//        }
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
