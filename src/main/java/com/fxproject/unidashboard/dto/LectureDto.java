package com.fxproject.unidashboard.dto;

import java.time.LocalDateTime;

public class LectureDto {

    private String topic;
    private LocalDateTime date;
    private String groupName;
    private String subjectName;

    public LectureDto() {
    }

    public LectureDto(String topic, LocalDateTime date, String groupName, String subjectName) {
        this.topic = topic;
        this.date = date;
        this.groupName = groupName;
        this.subjectName = subjectName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
