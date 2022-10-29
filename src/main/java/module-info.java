module com.unidashboard.universitydashboard {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.unidashboard.universitydashboard to javafx.fxml;
    exports com.unidashboard.universitydashboard;
}