package com.fxproject.unidashboard.email;

import com.fxproject.unidashboard.model.Professors;
import com.fxproject.unidashboard.model.Students;

public class EmailAddressGenerator {

    private static final String STUDENT_SUFFIX = "@uni.stud.com";
    private static final String PROFESSOR_SUFFIX = "@uni.prof.com";

    private EmailAddressGenerator() {
    }

    public static String generateMailForStudent(Students student) {
        return student.getFirstName().substring(0, 1) + student.getLastName().substring(0, 1) +
                student.getAlbumId() + STUDENT_SUFFIX;
    }

    public static String generateMailForProfessor(Professors professors) {
        return professors.getFirstName().substring(0, 1) + professors.getLastName().substring(0, 1) +
                professors.getAlbumId() + PROFESSOR_SUFFIX;
    }
}
