package com.example.phase2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.io.IOException;

public class LoginMenuController {
    @FXML
    Hyperlink forgotPassword;
    @FXML
    Label recoverPassword, yourPassword;
    public void exit() {
        Platform.exit();
    }
    public void showForgotPassword() {
        forgotPassword.setVisible(!forgotPassword.isVisible());
        recoverPassword.setVisible(!recoverPassword.isVisible());
        yourPassword.setVisible(!yourPassword.isVisible());
    }
    public void loginComplete() throws IOException {
        FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("CustomerMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        HelloApplication.getStage().setScene(scene);
    }
    public void goBackToMainMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        HelloApplication.getStage().setScene(scene);
    }
}