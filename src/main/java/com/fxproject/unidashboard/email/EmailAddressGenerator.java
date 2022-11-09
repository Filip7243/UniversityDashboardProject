package com.fxproject.unidashboard.email;

import com.fxproject.unidashboard.model.Professor;

import java.util.Random;

public class EmailAddressGenerator {

    public static String generateUniversityEmailForProfessor(Professor professor) {
        char firstLetterName = professor.getFirstName().toLowerCase().charAt(0);
        char lastLetterName = professor.getLastName().toLowerCase().charAt(0);
        Random random = new Random();
        int randomNumber = random.nextInt(10000) + 1;

        return String.valueOf(firstLetterName) + String.valueOf(lastLetterName) + randomNumber + "@.prof.uni.pl";
    }
}
