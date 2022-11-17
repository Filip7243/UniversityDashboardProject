package com.fxproject.unidashboard.service;

import com.fxproject.unidashboard.dto.WageDto;
import com.fxproject.unidashboard.mapper.WageMapper;
import com.fxproject.unidashboard.model.UniversityAccount;
import com.fxproject.unidashboard.model.UniversityMember;
import com.fxproject.unidashboard.model.Wage;
import com.fxproject.unidashboard.repository.UniversityAccountRepository;
import com.fxproject.unidashboard.repository.WageRepository;
import com.fxproject.unidashboard.repository.impl.UniversityAccountRepositoryImpl;
import com.fxproject.unidashboard.repository.impl.WageRepositoryImpl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import static com.fxproject.unidashboard.mapper.WageMapper.mapToWageDto;
import static com.fxproject.unidashboard.mapper.WageMapper.mapToWageDtos;

public class WageService {

    private final WageRepository wageRepository = new WageRepositoryImpl();
    private final UniversityAccountRepository accountRepository = new UniversityAccountRepositoryImpl();
    private static final DecimalFormat df = new DecimalFormat("0.000");

    public void addWage(UniversityMember member, Integer hoursWorked) {
        UniversityAccount account = member.getUniversityAccount();
        double hourlyRate;
        switch (account.getRole()) {
            case ROLE_PROFESSOR -> hourlyRate = 40.20;
            case ROLE_WORKER -> hourlyRate = 28.30;
            default -> hourlyRate = 0.0;
        }
        Wage wage = new Wage();
        wage.setPayday(LocalDateTime.now());
        wage.setHourlyRate(hourlyRate);
        if(hourlyRate > 0.0) {
            wage.setSalary(Double.valueOf(df.format(hourlyRate * hoursWorked)));
        } else {
            wage.setSalary(0.0);
        }
        wage.setEmployee(member);
        wage.setHoursWorked(hoursWorked);

        wageRepository.save(wage);
    }

    public void removeWageWithId(Long id) {
        wageRepository.removeWithId(id);
    }

    public WageDto findWageWithId(Long id) {
        Wage wage = wageRepository.findWithId(id).orElseThrow();
        return mapToWageDto(wage);
    }

    public List<WageDto> findAllWages() {
        List<Wage> all = wageRepository.findAll();
        return mapToWageDtos(all);
    }

    public List<WageDto> findWagesByPayday(LocalDateTime time) { // todo: improve it
        List<Wage> all = wageRepository.findWagesByPayday(time);
        return mapToWageDtos(all);
    }

    public List<WageDto> findWagesByEmployeePesel(String pesel) {
        List<Wage> all = wageRepository.findWagesByEmployeePesel(pesel);
        return mapToWageDtos(all);
    }

}
