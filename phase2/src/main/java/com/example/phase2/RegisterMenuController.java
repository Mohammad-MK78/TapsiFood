package com.example.phase2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RegisterMenuController {
    @FXML
    Button register, backToMainMenu, backToRegisterMenu;
    @FXML
    Label securityQuestionNotice;
    @FXML
    HBox registerHBox;
    @FXML
    VBox welcomeVBox, registerChoices;
    public void exit() {
        Platform.exit();
    }
    public void register() {
        registerChoices.setVisible(!registerChoices.isVisible());
        welcomeVBox.setVisible(!welcomeVBox.isVisible());
        securityQuestionNotice.setVisible(!securityQuestionNotice.isVisible());
        registerHBox.setVisible(!registerHBox.isVisible());
        register.setVisible(!register.isVisible());
        backToRegisterMenu.setVisible(true);
        backToMainMenu.setVisible(false);
    }
    public void registerComplete() throws IOException {
        FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        HelloApplication.getStage().setScene(scene);
    }
    public void goBackToMainMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        HelloApplication.getStage().setScene(scene);
    }
    public void goBackToRegisterMenu() {
        registerChoices.setVisible(!registerChoices.isVisible());
        welcomeVBox.setVisible(!welcomeVBox.isVisible());
        registerHBox.setVisible(!registerHBox.isVisible());
        securityQuestionNotice.setVisible(!securityQuestionNotice.isVisible());
        register.setVisible(!register.isVisible());
        backToMainMenu.setVisible(true);
        backToRegisterMenu.setVisible(false);
    }
}