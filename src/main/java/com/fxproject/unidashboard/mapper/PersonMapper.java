package com.fxproject.unidashboard.mapper;

import com.fxproject.unidashboard.dto.PersonDto;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.Professors;
import com.fxproject.unidashboard.model.Students;

import java.util.List;

public class PersonMapper {

    private PersonMapper(){}

    public static List<PersonDto> mapToPersonDtos(List<? extends Person> people) {
        return people.stream().map(p -> {
            if(p instanceof Students s) {
                return new PersonDto(s.getFirstName(), s.getLastName(), s.getEmail(),
                        String.valueOf(s.getAlbumId()), s.getAcc().getRole().name(), s.getAcc().getEnabled());
            } else {
                Professors s = (Professors) p;
                return new PersonDto(s.getFirstName(), s.getLastName(), s.getEmail(),
                        String.valueOf(s.getAlbumId()), s.getAcc().getRole().name(), s.getAcc().getEnabled());
            }
        }).toList();
    }
}
