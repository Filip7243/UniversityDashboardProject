package com.fxproject.unidashboard.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LectureDto {

    private Long id;
    private String topic;
    private LocalDate date;
    private String groupName;
    private String subjectName;

    public LectureDto() {
    }

    public LectureDto(Long id, String topic, LocalDate date, String groupName, String subjectName) {
        this.id = id;
        this.topic = topic;
        this.date = date;
        this.groupName = groupName;
        this.subjectName = subjectName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
