package com.fxproject.unidashboard.mapper;

import com.fxproject.unidashboard.dto.WageDto;
import com.fxproject.unidashboard.model.Wage;

import java.util.List;

public class WageMapper {

    private WageMapper(){}

    public static WageDto mapToWageDto(Wage wage) {
        return new WageDto(
                wage.getSalary(),
                wage.getEmployee(),
                wage.getHourlyRate(),
                wage.getHoursWorked(),
                wage.getPayday()
        );
    }


    public static List<WageDto> mapToWageDtos(List<Wage> all) {
        return all.stream().map(WageMapper::mapToWageDto).toList();
    }
}
