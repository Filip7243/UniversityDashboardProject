package com.fxproject.unidashboard.controller;

import com.fxproject.unidashboard.model.Marks;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.repository.MarkRepository;
import com.fxproject.unidashboard.utils.UserSession;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.List;

import static com.fxproject.unidashboard.controller.StudentController.loadFXMLItem;

public class StudentMarksController {

    @FXML
    private BorderPane borderPane;
    private static final Person loggedInUser = UserSession.getSession().getPerson();
    private MarkRepository mr = new MarkRepository();

    public void initialize() throws IOException {
        List<Marks> studentMarks = mr.findStudentMarks(((Students) loggedInUser).getAlbumId());
        VBox marksBox = new VBox();
        marksBox.setAlignment(Pos.CENTER);
        marksBox.setPrefWidth(borderPane.getPrefWidth());

        for (int j = 0; j < studentMarks.size(); j++) { // j < count(student's marks in current subject(i))
            HBox markInfoBox = loadFXMLItem();
            markInfoBox.setPrefWidth(0.85*marksBox.getPrefWidth());
            marksBox.setSpacing(10);
            marksBox.getChildren().add(markInfoBox);
            StackPane sP = (StackPane) markInfoBox.lookup("#pane");
            Label markInfoLabel = (Label) markInfoBox.lookup("#label");
            Marks mark = studentMarks.get(j);
            markInfoLabel.setText(
                    mark.getSubject().getName() + " | " +
                            mark.getType().name() + " " + "| " +
                            mark.getMarkDate().toLocalDate().toString()
            );
            Label markLabel = new Label();
            markLabel.setText(String.valueOf(mark.getMark()));
            markLabel.setFont(new Font(18));
            markLabel.setTextFill(Color.color(0.5, 0, 1));
            StackPane.setAlignment(markLabel, Pos.CENTER);
            sP.getChildren().add(markLabel);
        }
        borderPane.setCenter(marksBox);
    }


}
