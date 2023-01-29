package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.dto.AttendanceForm;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.AttendanceRepository;
import com.fxproject.unidashboard.repository.LectureRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.UserSession;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static com.fxproject.unidashboard.mapper.AttendanceMapper.mapToAttendanceForm;

public class AttendanceController {
    @FXML
    private TableView<AttendanceForm> attendanceList;
    @FXML
    private ComboBox<Lectures> professorLectures;
    @FXML
    private TableColumn<AttendanceForm, Boolean> lateCol;
    @FXML
    private TableColumn<AttendanceForm, Boolean> presentCol;
    @FXML
    private TableColumn<AttendanceForm, Students> studentCol;
    private LectureRepository lr = new LectureRepository();
    private StudentRepository sr = new StudentRepository();
    private AttendanceRepository ar = new AttendanceRepository();

    public void initialize() {
        Professors professor = (Professors) UserSession.getSession().getPerson();
        List<Lectures> lectures = lr.findProfessorLectures(professor);
        professorLectures.setItems(FXCollections.observableArrayList(lectures));
        lateCol.setCellValueFactory(new PropertyValueFactory<>("late"));
        presentCol.setCellValueFactory(new PropertyValueFactory<>("present"));
        // adding checkboxes to cell
        lateCol.setCellFactory(CheckBoxTableCell.forTableColumn(lateCol));
        presentCol.setCellFactory(CheckBoxTableCell.forTableColumn(presentCol));
        studentCol.setCellValueFactory(new PropertyValueFactory<>("student"));
        attendanceList.setEditable(true);
        presentCol.setEditable(true);
        lateCol.setEditable(true);

        professorLectures.valueProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null) {
                Groups group = newValue.getGroup();
                List<Students> studentsFromGroup = sr.getStudentsFromGroup(group);
                // map students to attendance form
                List<AttendanceForm> attendanceForms =
                        mapToAttendanceForm(studentsFromGroup);
                ObservableList<AttendanceForm> data = FXCollections.observableArrayList(attendanceForms);
                attendanceList.setItems(data);
            }
        });
    }

    public void checkAttendance() {
        // map to attendances class
        List<Attendances> attendances = attendanceList.getItems().stream().map(af ->
                new Attendances(null, af.getPresent(), af.getLate(), professorLectures.getValue(), af.getStudent())).toList();
        ar.save(attendances);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Attendance Check Done!");
        a.show();
        cancelAttendance();
    }

    public void cancelAttendance() {
        professorLectures.getSelectionModel().clearSelection();
        attendanceList.setItems(FXCollections.observableArrayList());
    }
}
