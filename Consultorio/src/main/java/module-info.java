module com.example.consultorio {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.consultorio to javafx.fxml;
    exports com.example.consultorio;
}