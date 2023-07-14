package com.example.Final.FXMLController;

import com.example.Final.Controller.LoginMenuController;
import com.example.Final.Main;
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
import java.sql.SQLException;

public class FXAdminRegisterMenuController {
    @FXML
    Button registerButton, back;
    @FXML
    HBox registerHBox;
    @FXML
    TextField usernameInp;
    @FXML
    PasswordField passwordInp;
    @FXML
    Label registerStatus;
    public void openRegisterBox() {
        back.setVisible(!back.isVisible());
        registerButton.setVisible(!registerButton.isVisible());
        registerHBox.setVisible(!registerHBox.isVisible());
    }
    public void registerCompleted() throws SQLException, ClassNotFoundException, IOException {
        String username = usernameInp.getText();
        String password = passwordInp.getText();
        String result = LoginMenuController.setSnappFoodAdmin(username, password);
        if (result.equals("TapsiFood admin register successful")) {
            Main.setAdminRegistration(true);
            FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
            Scene scene = new Scene(Loader.load());
            Main.getStage().setScene(scene);
        }
        else
            registerStatus.setText(result);
    }
    public void registerWithEnter(KeyEvent keyEvent) throws SQLException, IOException, ClassNotFoundException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            registerCompleted();
        }

    }
}