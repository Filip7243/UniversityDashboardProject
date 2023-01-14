package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.StudentAttendanceOnLecture;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.AttendanceRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @FXML
    private ComboBox<Subjects> comboSubject;

    private static final Person loggedInUser = UserSession.getSession().getPerson();
    private AttendanceRepository ar = new AttendanceRepository();

    public void initialize() {
        List<Attendances> studentAttendances = ar.findStudentAttendances((Students) loggedInUser);
        Set<Subjects> subjects = studentAttendances.stream().map(Attendances::getLecture).collect(Collectors.toSet())
                .stream().map(Lectures::getSubject).collect(Collectors.toSet());
        comboSubject.setItems(FXCollections.observableArrayList(subjects));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        presentColumn.setCellValueFactory(new PropertyValueFactory<>("isPresent"));
        List<StudentAttendanceOnLecture> dtos = mapToStudentAttendancesDtos(studentAttendances);
        FilteredList<StudentAttendanceOnLecture> filteredList =
                new FilteredList<>(FXCollections.observableArrayList(dtos), a -> true);
        // filtering by combobox choice
        comboSubject.valueProperty().addListener((obs, oldValue, newValue) -> {
            filteredList.setPredicate(attendance -> {
                if (newValue == null) {
                    return true; // displays all because any value was chosen
                }
                return attendance.getSubjectName().equals(newValue.getName()); // displays only chosen value in combobox
            });
        });
        table.setItems(filteredList);
    }


}
