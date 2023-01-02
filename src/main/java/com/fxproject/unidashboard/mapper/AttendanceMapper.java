package com.fxproject.unidashboard.mapper;

import com.fxproject.unidashboard.dto.StudentAttendanceOnLecture;
import com.fxproject.unidashboard.model.Attendances;
import com.fxproject.unidashboard.model.Lectures;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

public class AttendanceMapper {

    public static List<StudentAttendanceOnLecture> mapToStudentAttendancesDtos(List<Attendances> studentAttendances) {
        return studentAttendances.stream().map(a -> {
            Lectures lecture = a.getLecture();
            return new StudentAttendanceOnLecture(lecture.getSubject().getName(), lecture.getLectureDate().toLocalDate(),
                    String.valueOf(a.getPresent()));
        }).toList();
    }
}
