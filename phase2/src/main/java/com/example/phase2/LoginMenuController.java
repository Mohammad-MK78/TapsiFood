package com.example.phase2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class LoginMenuController {
    @FXML
    Hyperlink forgotPassword;
    @FXML
    Label recoverPassword, yourPassword, securityLabel, wrongSecurityQuestion, notAppropriatePassword;
    @FXML
    TextField securityQuestion, password;
    public void exit() {
        Platform.exit();
    }
    public void showSecurityQuestionForForgottenPassword() {
        forgotPassword.setVisible(false);
        securityLabel.setVisible(true);
        securityQuestion.setVisible(true);
    }
    public void showPassword() {
        if (!securityQuestion.getText().equals("")) {
            if (securityQuestion.getText().equals("doroste")) {
                yourPassword.setVisible(true);
                recoverPassword.setVisible(true);
                wrongSecurityQuestion.setVisible(false);
            }
            else {
                wrongSecurityQuestion.setVisible(true);
            }
        }
    }
    public void showPasswordWithEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
            showPassword();
    }
    public void loginWithEnterKey(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER)
            loginComplete();
    }
    public void loginComplete() throws IOException {
        if (!password.getText().equals("")) {
            if (password.getText().equals("farzan2831")) {
                FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("CustomerMenu.fxml"));
                Scene scene = new Scene(Loader.load());
                HelloApplication.getStage().setScene(scene);
            } else {
                notAppropriatePassword.setVisible(true);
            }
        }
    }
    public void goBackToMainMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        HelloApplication.getStage().setScene(scene);
    }
}