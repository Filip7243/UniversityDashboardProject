package com.fxproject.unidashboard.model;

public enum TypeOfStudy {
    BACHELOR("Bachelor", 3.0),
    MASTERS("Masters", 2.0),
    ENGINEERING("Engineering", 3.5);

    private final String name;
    private final Double duration;

    TypeOfStudy(String name, Double duration) {
        this.name = name;
        this.duration = duration;
    }
}
