package com.fxproject.unidashboard.mapper;

import com.fxproject.unidashboard.dto.YearDto;
import com.fxproject.unidashboard.model.Year;

import java.util.List;
import java.util.stream.Collectors;

public class YearMapper {

    private YearMapper() {
    }

    public static YearDto mapToYearDto(Year year) {
        return new YearDto(
                year.getName(),
                year.getFieldOfStudy(),
                year.getYearOfStudy(),
                year.getSubjects()
        );
    }


    public static List<YearDto> mapToYearDtos(List<Year> all) {
        return all.stream().map(YearMapper::mapToYearDto).collect(Collectors.toList());
    }
}
