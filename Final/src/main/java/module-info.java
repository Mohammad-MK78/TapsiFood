module com.example.Final {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.Final to javafx.fxml, javafx.base;
    exports com.example.Final;
    exports com.example.Final.Model;
    exports com.example.Final.FXMLController;
    opens com.example.Final.Model to javafx.base;
    opens com.example.Final.FXMLController to javafx.fxml;
}