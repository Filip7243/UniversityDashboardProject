package com.fxproject.unidashboard;

import com.fxproject.unidashboard.dto.*;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.*;
import com.fxproject.unidashboard.repository.impl.*;
import com.fxproject.unidashboard.service.*;
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


    }

}