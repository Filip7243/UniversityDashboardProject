package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Wage;

import java.time.LocalDateTime;
import java.util.List;

public interface WageRepository extends DefaultRepository<Wage, Long>{

    List<Wage> findWagesByPayday(LocalDateTime payday);
    List<Wage> findWagesByEmployeePesel(String pesel);
}
