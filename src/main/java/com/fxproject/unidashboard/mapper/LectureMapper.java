package com.fxproject.unidashboard.mapper;

import com.fxproject.unidashboard.dto.LectureDto;
import com.fxproject.unidashboard.model.Lectures;

import java.util.List;
import java.util.stream.Stream;

public class LectureMapper {

    private LectureMapper() {
    }

    public static List<LectureDto> mapToLectureDtos(List<Lectures> allLectures) {
        return allLectures.stream()
                .map(l -> new LectureDto(l.getId(), l.getLectureTopic(), l.getLectureDate().toLocalDate(), l.getGroup().getName(), l.getSubject().getName()))
                .toList();
    }
}
