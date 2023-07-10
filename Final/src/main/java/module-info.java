module com.example.phase2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.Final to javafx.fxml;
    exports com.example.Final;
}