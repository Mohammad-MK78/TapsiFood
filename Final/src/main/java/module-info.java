module com.example.Final {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.Final to javafx.fxml;
    exports com.example.Final;
}