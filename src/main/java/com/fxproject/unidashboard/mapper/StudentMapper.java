package com.fxproject.unidashboard.mapper;

import com.fxproject.unidashboard.dto.StudentDto;
import com.fxproject.unidashboard.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    private StudentMapper() {
    }

    public static StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
                student.getAlbumId(),
                student.getFirstName(),
                student.getSecondName(),
                student.getLastName(),
                student.getEmail(),
                student.getDateOfBirth(),
                student.getPlaceOfBirth(),
                student.getPhoneNumber(),
                student.getAlbumId(),
                student.getYears()
        );
    }


    public static List<StudentDto> mapToStudentDtos(List<Student> all) {
        return all.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());
    }
}
