package com.fxproject.unidashboard.model;

public enum TypeOfStudy {
    BACHELOR("Bachelor", 3.0),
    MASTERS("Masters", 2.0),
    ENGINEERING("Engineering", 3.5),
    DOCTORAL("Doctoral", 4.0);

    private final String name;
    private final Double duration;

    TypeOfStudy(String name, Double duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public Double getDuration() {
        return duration;
    }
}
