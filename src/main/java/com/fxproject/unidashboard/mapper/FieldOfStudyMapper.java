package com.fxproject.unidashboard.mapper;

import com.fxproject.unidashboard.dto.FieldOfStudyDto;
import com.fxproject.unidashboard.model.FieldOfStudy;

import java.util.List;

public class FieldOfStudyMapper {

    private FieldOfStudyMapper() {
    }

    public static FieldOfStudy mapToFieldOfStudy(FieldOfStudyDto fieldOfStudyDto) {
        FieldOfStudy fieldOfStudy = new FieldOfStudy();
        fieldOfStudy.setName(fieldOfStudyDto.getName());
        fieldOfStudy.setType(fieldOfStudyDto.getType());
        fieldOfStudy.setDepartment(fieldOfStudyDto.getDepartment());
        return fieldOfStudy;
    }


    public static FieldOfStudyDto mapToFieldOfStudyDto(FieldOfStudy fieldOfStudy) {
        return new FieldOfStudyDto(
                fieldOfStudy.getName(),
                fieldOfStudy.getType(),
                fieldOfStudy.getDepartment()
        );
    }

    public static List<FieldOfStudyDto> mapToFieldOfStudyDtos(List<FieldOfStudy> all) {
        return all.stream()
                .map(FieldOfStudyMapper::mapToFieldOfStudyDto)
                .toList();
    }
}
