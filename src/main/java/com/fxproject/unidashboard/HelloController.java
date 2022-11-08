package com.fxproject.unidashboard;

import com.fxproject.unidashboard.dto.StudentDto;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.FieldOfStudyRepository;
import com.fxproject.unidashboard.repository.ProfessorRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.repository.UniversityAccountRepository;
import com.fxproject.unidashboard.repository.impl.FieldOfStudyRepositoryImpl;
import com.fxproject.unidashboard.repository.impl.ProfessorRepositoryImpl;
import com.fxproject.unidashboard.repository.impl.StudentRepositoryImpl;
import com.fxproject.unidashboard.repository.impl.UniversityAccountRepositoryImpl;
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


    public void initialize() {
        ProfessorRepositoryImpl repo = new ProfessorRepositoryImpl();
        System.out.println(repo.findProfessorByUniversityEmail("mk@mail.com"));
    }

}