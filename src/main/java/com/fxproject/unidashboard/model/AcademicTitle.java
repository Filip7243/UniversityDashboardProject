package com.fxproject.unidashboard.model;

public enum AcademicTitle {

    ENGINEER("Engineer", "Eng"),
    MASTER("Master", "M"),
    DOCTOR("Doctor", "Doc");

    private final String title;
    private final String shortcut;

    AcademicTitle(String title, String shortcut) {
        this.title = title;
        this.shortcut = shortcut;
    }
}
