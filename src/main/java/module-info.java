module com.fxproject.unidashboard {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.hibernate.validator;
    requires com.jfoenix;
    requires fontawesomefx;
    requires com.h2database;
    requires java.naming;


    opens com.fxproject.unidashboard to javafx.fxml, org.hibernate.orm.core;
    opens com.fxproject.unidashboard.controller to javafx.fxml;
    opens com.fxproject.unidashboard.model to org.hibernate.orm.core;
    exports com.fxproject.unidashboard;
    exports com.fxproject.unidashboard.controller;
    exports com.fxproject.unidashboard.dto;
}