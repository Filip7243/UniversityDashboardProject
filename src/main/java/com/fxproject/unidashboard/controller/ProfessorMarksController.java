package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.MarkRepository;
import com.fxproject.unidashboard.repository.ProfessorsSubjectsInGroupsRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfessorMarksController {

    @FXML
    private TableView<Marks> marksTable;

    @FXML
    private ComboBox<Groups> comboGroups;

    @FXML
    private ComboBox<Students> comboStudents;

    @FXML
    private ComboBox<Subjects> comboSubjects;

    @FXML
    private TableColumn<Marks, LocalDateTime> dateCol;

    @FXML
    private TableColumn<Marks, String> descriptionCol;

    @FXML
    private TableColumn<Marks, Double> markCol;
    private StudentRepository sr = new StudentRepository();
    private ProfessorsSubjectsInGroupsRepository psigr = new ProfessorsSubjectsInGroupsRepository();
    private MarkRepository mr = new MarkRepository();
    private ObservableList<Marks> studentMarks = FXCollections.observableArrayList();

    public void initialize() {
        // enable editing
        marksTable.setEditable(true);

        // description
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setOnEditCommit(e -> {
            Marks currentMark = e.getTableView().getItems().get(e.getTablePosition().getRow());
            Marks newMark = currentMark;
            newMark.setDescription(e.getNewValue());
            mr.updateMark(newMark, currentMark);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Description Updated!");
            alert.show();
        });


        // mark
        markCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        markCol.setOnEditCommit(e -> {
            Marks currentMark = e.getTableView().getItems().get(e.getTablePosition().getRow());
            Marks newMark = currentMark;
            newMark.setMark(e.getNewValue());
            mr.updateMark(newMark, currentMark);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mark Updated!");
            alert.show();
        });

        // delete button column
        TableColumn<Marks, Button> buttonCol = new TableColumn<>();
        buttonCol.setCellFactory(col -> {
            TableCell<Marks, Button> buttonTableCell = new TableCell<>() {
                Button btn = new Button("DELETE");

                @Override
                protected void updateItem(Button button, boolean empty) {
                    super.updateItem(button, empty);
                    if (!empty) {
                        btn.setBackground(Background.fill(Paint.valueOf("red")));
                        setGraphic(btn);

                        btn.setOnAction(e -> {
                            Marks selectedItem = getTableView().getItems().get(getIndex());
                            System.out.println(selectedItem);
                            mr.remove(selectedItem);
                            getTableView().getItems().remove(getIndex());
                        });
                    }
                }
            };
            return buttonTableCell;
        });

        dateCol.setCellValueFactory(new PropertyValueFactory<>("markDate"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        markCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        Professors professor = (Professors) UserSession.getSession().getPerson();
        // maps to set of groups
        Set<Groups> groups = professor.getPsig().stream().map(ProfessorsSubjectsInGroups::getGroup).collect(Collectors.toSet());
        comboGroups.setItems(FXCollections.observableArrayList(groups));

        // listener
        comboGroups.valueProperty().addListener((obs, oldValue, newValue) -> {
            // professor's subjects in group
            comboSubjects.setItems(FXCollections.observableArrayList(psigr.findProfessorSubjectInGroup(newValue, professor)));
            // students in group on subject
            comboStudents.setItems(FXCollections.observableArrayList(sr.getStudentsFromGroup(newValue)));
        });

        if (!comboStudents.getItems().isEmpty() || comboStudents != null) {
            comboStudents.valueProperty().addListener((obs, oldValue, newValue) -> {
                // set student's marks in Observable List
                studentMarks.setAll(mr.findStudentMarks(newValue.getAlbumId()));
                // populate data on table
                marksTable.setItems(studentMarks);
            });
        }

        marksTable.getColumns().add(buttonCol);

    }


}