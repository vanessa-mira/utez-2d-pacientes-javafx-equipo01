module com.example.consultorio {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.consultorio to javafx.fxml;
    opens com.example.consultorio.controllers to javafx.fxml;
    opens com.example.consultorio.models to javafx.base;
    exports com.example.consultorio;
}