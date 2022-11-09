package com.fxproject.unidashboard;

import com.fxproject.unidashboard.dto.ProfessorDto;
import com.fxproject.unidashboard.dto.StudentDto;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.*;
import com.fxproject.unidashboard.repository.impl.*;
import com.fxproject.unidashboard.service.ProfessorService;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HelloController {
    @FXML
    private TextField idInput;
    @FXML
    private ListView<StudentDto> studentsDtoList;
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


    public void initialize() { //todo: repair removeWithId in class that inherited from UniversityMember

        ProfessorService service = new ProfessorService();
        YearRepository yearRepository = new YearRepositoryImpl();
        SubjectRepository subjectRepository = new SubjectRepositoryImpl();
        FieldOfStudyRepository fieldOfStudyRepository = new FieldOfStudyRepositoryImpl();
        UniversityDepartmentRepository universityDepartmentRepository = new UniversityDepartmentRepositoryImpl();
//
//        UniversityDepartment department = new UniversityDepartment(null, "Kolegium nak przyrodniczych");
//        universityDepartmentRepository.save(department);
//        FieldOfStudy fieldOfStudy = new FieldOfStudy(null, "Informatyka", TypeOfStudy.ENGINEERING, department);
//        fieldOfStudyRepository.save(fieldOfStudy);
//        Year year = new Year(null, "2022/2025", new ArrayList<>(), fieldOfStudy, 2, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>());
//        yearRepository.save(year);
//        Subject subject = new Subject(null, "ez");
//        subjectRepository.save(subject);

        service.updateProfessor(6L, new ProfessorDto(
                "Kamil", "Kamilowski", "Kowalski",
                "kamil@mail.com", LocalDateTime.now(), "dsadsa", "fajny-numer",
                "326153271312", AcademicTitle.MASTER
        ));
    }

}