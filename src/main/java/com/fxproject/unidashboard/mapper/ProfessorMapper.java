package com.fxproject.unidashboard.mapper;

import com.fxproject.unidashboard.dto.ProfessorDto;
import com.fxproject.unidashboard.model.Professor;

import java.util.ArrayList;

public class ProfessorMapper {

    private ProfessorMapper() {
    }

    public static Professor mapToProfessor(ProfessorDto professorDto) {
        Professor professor = new Professor();
        professor.setFirstName(professorDto.getFirstName());
        professor.setSecondName(professorDto.getSecondName());
        professor.setLastName(professorDto.getLastName());
        professor.setEmail(professorDto.getEmail());
        professor.setDateOfBirth(professorDto.getDateOfBirth());
        professor.setPlaceOfBirth(professorDto.getPlaceOfBirth());
        professor.setPhoneNumber(professorDto.getPhoneNumber());
        professor.setPesel(professorDto.getPesel());
        professor.setAcademicTitle(professorDto.getAcademicTitle());
        professor.setYears(new ArrayList<>());
        professor.setSubjects(new ArrayList<>());
        return professor;
    }

}
