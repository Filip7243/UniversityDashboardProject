package com.fxproject.unidashboard.dto;

import com.fxproject.unidashboard.model.Students;
import javafx.beans.property.SimpleBooleanProperty;

public class AttendanceForm {

    private Students student;
    private SimpleBooleanProperty present;
    private SimpleBooleanProperty late;

    public AttendanceForm() {
    }

    public AttendanceForm(Students student) {
        this.student = student;
        this.present = new SimpleBooleanProperty(false);
        this.late = new SimpleBooleanProperty(false);
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Boolean getPresent() {
        return present.get();
    }

    public void setPresent(Boolean present) {
        this.present.set(present);
    }

    public Boolean getLate() {
        return late.get();
    }

    public void setLate(Boolean late) {
        this.late.set(late);
    }

    public SimpleBooleanProperty presentProperty() {
        return present;
    }

    public SimpleBooleanProperty lateProperty() {
        return late;
    }

}
