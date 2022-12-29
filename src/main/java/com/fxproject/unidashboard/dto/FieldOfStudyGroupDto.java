package com.fxproject.unidashboard.dto;

public class FieldOfStudyGroupDto {

    private String filedOfStudyName;
    private String groupName;

    public FieldOfStudyGroupDto(String filedOfStudyName, String groupName) {
        this.filedOfStudyName = filedOfStudyName;
        this.groupName = groupName;
    }

    public FieldOfStudyGroupDto() {
    }

    public String getFiledOfStudyName() {
        return filedOfStudyName;
    }

    public void setFiledOfStudyName(String filedOfStudyName) {
        this.filedOfStudyName = filedOfStudyName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
