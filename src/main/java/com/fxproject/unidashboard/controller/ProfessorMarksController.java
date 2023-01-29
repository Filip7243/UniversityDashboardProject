package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.MarkRepository;
import com.fxproject.unidashboard.repository.ProfessorsSubjectsInGroupsRepository;
import com.fxproject.unidashboard.repository.StudentRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.util.converter.DoubleStringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    private TableColumn<Marks, LocalDate> dateCol;

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
            if(e.getNewValue() >= 2.0 && e.getNewValue() <= 5.0) {
                if (e.getNewValue() % 0.5 == 0) {
                    newMark.setMark(e.getNewValue());
                } else {
                    double round = Math.round(e.getNewValue());
                    newMark.setMark(round);
                    currentMark.setMark(round);
                    marksTable.refresh();
                }
                mr.updateMark(newMark, currentMark);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mark Updated!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Mark!");
                alert.show();
                marksTable.refresh();
            }

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
                            Alert a = new Alert(Alert.AlertType.WARNING, "You sure you want to delete it?");
                            Optional<ButtonType> result = a.showAndWait();
                            if (result.isPresent()) {
                                if (result.get() == ButtonType.OK) {
                                    Marks selectedItem = getTableView().getItems().get(getIndex());
                                    System.out.println(selectedItem);
                                    mr.remove(selectedItem);
                                    getTableView().getItems().remove(getIndex());
                                    marksTable.refresh();
                                }
                            }
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
        List<ProfessorsSubjectsInGroups> professorPSIG = psigr.findProfessorPSIG(professor);
        Set<Groups> groups = professorPSIG.stream().map(ProfessorsSubjectsInGroups::getGroup).collect(Collectors.toSet());
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
