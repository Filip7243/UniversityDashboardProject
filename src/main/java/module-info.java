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

    opens com.fxproject.unidashboard to javafx.fxml, org.hibernate.orm.core;
    opens com.fxproject.unidashboard.model to org.hibernate.orm.core;
    exports com.fxproject.unidashboard;
}