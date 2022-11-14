package com.fxproject.unidashboard.service;

import com.fxproject.unidashboard.dto.FieldOfStudyDto;
import com.fxproject.unidashboard.mapper.FieldOfStudyMapper;
import com.fxproject.unidashboard.model.FieldOfStudy;
import com.fxproject.unidashboard.model.TypeOfStudy;
import com.fxproject.unidashboard.model.UniversityDepartment;
import com.fxproject.unidashboard.repository.FieldOfStudyRepository;
import com.fxproject.unidashboard.repository.UniversityDepartmentRepository;
import com.fxproject.unidashboard.repository.impl.FieldOfStudyRepositoryImpl;
import com.fxproject.unidashboard.repository.impl.UniversityDepartmentRepositoryImpl;

import java.util.List;

import static com.fxproject.unidashboard.mapper.FieldOfStudyMapper.*;

public class FieldOfStudyService {

    private final FieldOfStudyRepository fieldOfStudyRepository = new FieldOfStudyRepositoryImpl();
    private final UniversityDepartmentRepository universityDepartmentRepository = new UniversityDepartmentRepositoryImpl();


    public void addFieldOfStudy(FieldOfStudyDto fieldOfStudyDto) {
        FieldOfStudy fieldOfStudy = mapToFieldOfStudy(fieldOfStudyDto);
        fieldOfStudyRepository.save(fieldOfStudy);
    }

    public FieldOfStudyDto findFieldOfStudyWithId(Long id) {
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findWithId(id).orElseThrow();//todo: custom exception
        return mapToFieldOfStudyDto(fieldOfStudy);
    }

    public void removeFieldOfStudy(Long id) {
        fieldOfStudyRepository.removeWithId(id);
    }

    public List<FieldOfStudyDto> findAllFieldsOfStudy() {
        List<FieldOfStudy> all = fieldOfStudyRepository.findAll();
        return mapToFieldOfStudyDtos(all);
    }

    public FieldOfStudyDto findFieldOfStudyByName(String name) {
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findFieldOfStudyByName(name).orElseThrow(); //todo: custom exception
        return mapToFieldOfStudyDto(fieldOfStudy);
    }

    public List<FieldOfStudyDto> findFieldOfStudyByType(TypeOfStudy typeOfStudy) {
        List<FieldOfStudy> all = fieldOfStudyRepository.findFieldOfStudyByType(typeOfStudy);
        return mapToFieldOfStudyDtos(all);
    }

    public UniversityDepartment findFieldOfStudyDepartment(Long id) {
        return fieldOfStudyRepository.findFieldOfStudyDepartment(id).orElseThrow(); //todo: custom exception
    }

    public List<FieldOfStudyDto> findFieldsOfStudyByDepartment(String departmentName) {
        UniversityDepartment department = universityDepartmentRepository.findDepartmentWithName(departmentName).orElseThrow();
        List<FieldOfStudy> all = fieldOfStudyRepository.findFieldsOfStudyByDepartment(department);
        return mapToFieldOfStudyDtos(all);
    }

    public void addDepartmentToFieldOfStudy(Long id, String departmentName) {
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findWithId(id).orElseThrow();//todo: cusotm excpetion
        UniversityDepartment department = universityDepartmentRepository.findDepartmentWithName(departmentName).orElseThrow();

        fieldOfStudy.setDepartment(department);
        fieldOfStudyRepository.save(fieldOfStudy);
    }

    public void updateFieldOfStudy(Long id, FieldOfStudyDto fieldOfStudyDto) {
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findWithId(id).orElseThrow();
        fieldOfStudy.setName(fieldOfStudyDto.getName());
        fieldOfStudy.setType(fieldOfStudyDto.getType());
        fieldOfStudy.setDepartment(fieldOfStudyDto.getDepartment());

        fieldOfStudyRepository.save(fieldOfStudy);
    }
}
