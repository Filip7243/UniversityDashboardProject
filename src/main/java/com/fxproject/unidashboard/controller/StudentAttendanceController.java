package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.StudentAttendanceOnLecture;
import com.fxproject.unidashboard.model.Attendances;
import com.fxproject.unidashboard.model.Lectures;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.repository.AttendanceRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

import static com.fxproject.unidashboard.mapper.AttendanceMapper.mapToStudentAttendancesDtos;

public class StudentAttendanceController {

    @FXML
    private TableView<StudentAttendanceOnLecture> table;
    @FXML
    private TableColumn<StudentAttendanceOnLecture, String> subjectColumn;
    @FXML
    private TableColumn<StudentAttendanceOnLecture, LocalDate> dateColumn;
    @FXML
    private TableColumn<StudentAttendanceOnLecture, String> presentColumn;

    private static final Person loggedInUser = UserSession.getSession().getPerson();
    private AttendanceRepository ar = new AttendanceRepository();

    public void initialize() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        presentColumn.setCellValueFactory(new PropertyValueFactory<>("isPresent"));
        List<StudentAttendanceOnLecture> dtos = mapToStudentAttendancesDtos(ar.findStudentAttendances((Students) loggedInUser));
        table.setItems(FXCollections.observableArrayList(dtos));
    }


}
