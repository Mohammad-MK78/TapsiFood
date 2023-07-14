package com.example.Final.FXMLController;

import com.example.Final.Controller.LoginMenuController;
import com.example.Final.Main;
import com.example.Final.View.LoginMenuEnums;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.*;

public class FXAdminRegisterMenuController {
    @FXML
    Button registerButton;
    @FXML
    HBox registerHBox;
    @FXML
    TextField usernameInp;
    @FXML
    PasswordField passwordInp;
    @FXML
    Label registerStatus;
    Boolean adminRegistered;
    public void enterApp() throws IOException, SQLException, ClassNotFoundException {
        checkAdminRegistered();
        FXMLLoader Loader;
        Scene scene;
        if (adminRegistered) {
            Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
            scene = new Scene(Loader.load());
            Main.getStage().setScene(scene);
        }
        else {
            registerButton.setVisible(false);
            registerHBox.setVisible(true);
        }
    }
    public void checkAdminRegistered() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlRegAdmin = "SELECT * FROM tapsifood.accounts where position='admin'";
        ResultSet regAdminCheck = statement.executeQuery(sqlRegAdmin);
        adminRegistered = regAdminCheck.next();

    }
    public void registerCompleted() throws SQLException, ClassNotFoundException, IOException {
        String username = usernameInp.getText();
        String password = passwordInp.getText();
        String result = LoginMenuController.setSnappFoodAdmin(username, password);
        if (result.equals("TapsiFood admin register successful")) {
            FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
            Scene scene = new Scene(Loader.load());
            Main.getStage().setScene(scene);
        }
    }
    public void registerWithEnter(KeyEvent keyEvent) throws SQLException, IOException, ClassNotFoundException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            registerCompleted();
        }
    }
}