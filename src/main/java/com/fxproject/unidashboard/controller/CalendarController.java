package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.Exams;
import com.fxproject.unidashboard.model.Groups;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.repository.ExamRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalendarController {

    @FXML
    private AnchorPane anchor;
    private Students loggedInUser = (Students) UserSession.getSession().getPerson();
    private ExamRepository er = new ExamRepository();
    private Map<LocalDate, Exams> datesWithExams = new HashMap<>();


    public void initialize() {
        Set<Groups> groups = loggedInUser.getGroups();
        for (Groups group : groups) {
            List<Exams> studentExams = er.findStudentExamsInGroup(group);
            for (Exams studentExam : studentExams) {
                datesWithExams.put(studentExam.getExamDate().toLocalDate(), studentExam);
            }
        }
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (datesWithExams.containsKey(item)) {
                            switch (datesWithExams.get(item).getExamType()) {
                                // sesja
                                case SEMESTER_EXAM -> {
                                    setTooltip(new Tooltip("EXAM DAY"));
                                    setStyle("-fx-background-color: #ff4444;");
                                    setText("Exam " + datesWithExams.get(item).getSubject().getName());

                                }
                                // kolokwium
                                case TEST -> {
                                    setTooltip(new Tooltip("TEST DAY"));
                                    setStyle("-fx-background-color: #428beb;");
                                    setText("Test " + datesWithExams.get(item).getSubject().getName());
                                }
                            }
                            setWrapText(true);
                            setTextAlignment(TextAlignment.CENTER);
                        } else {
                            setTooltip(null);
                            setStyle(null);
                        }
                    }
                };
            }
        };

        DatePicker dp = new DatePicker();
        dp.setDayCellFactory(dayCellFactory);
        DatePickerSkin dpSkin = new DatePickerSkin(dp);
        Node popupContent = dpSkin.getPopupContent();
        anchor.getChildren().add(popupContent);
    }
}
