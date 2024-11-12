module com.example.kianakooshesh_comp228_lab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.kianakooshesh_comp228_lab4 to javafx.fxml;
    exports com.example.kianakooshesh_comp228_lab4;

    // Responsible for exporting the exercise1 package so JavaFX can access it:
    exports exercise1;
}