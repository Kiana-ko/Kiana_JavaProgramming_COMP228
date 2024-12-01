module com.example.kianakooshesh_comp228_lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens exercise1 to javafx.fxml;
    exports exercise1;
}