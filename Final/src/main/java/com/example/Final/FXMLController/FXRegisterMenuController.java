package com.example.Final.FXMLController;

import com.example.Final.Controller.LoginMenuController;
import com.example.Final.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class FXRegisterMenuController {
    @FXML
    Button register, backToMainMenu, backToRegisterMenu, customerRegister, deliveryRegister;
    @FXML
    Label securityQuestionNotice;
    @FXML
    HBox registerHBox;
    @FXML
    VBox welcomeVBox, registerChoices;
    @FXML
    TextField usernameInp, locationInp, securityQuestionInp;
    @FXML
    PasswordField passwordInp;
    String position = "";
    public void exit() {
        Platform.exit();
    }
    public void chooseRegister(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        position = node.getAccessibleText();
        registerChoices.setVisible(!registerChoices.isVisible());
        welcomeVBox.setVisible(!welcomeVBox.isVisible());
        securityQuestionNotice.setVisible(!securityQuestionNotice.isVisible());
        registerHBox.setVisible(!registerHBox.isVisible());
        register.setVisible(!register.isVisible());
        backToRegisterMenu.setVisible(true);
        backToMainMenu.setVisible(false);
    }
    public void registerComplete() throws IOException, SQLException, ClassNotFoundException {
        boolean registerDone = false;
        String username = usernameInp.getText();
        String password = passwordInp.getText();
        int location = Integer.parseInt(locationInp.getText());
        String securityQuestion = securityQuestionInp.getText();
        String result;
        switch (position){
            case "customer":
                result = LoginMenuController.customerRegister(username, password, location, securityQuestion);
                if (result.equals("customer register successful"))
                    registerDone = true;
                break;
            case "delivery":
                result = LoginMenuController.deliveryRegister(username, password, location, securityQuestion);
                if (result.equals("delivery register successful"))
                    registerDone = true;
                break;
        }
        if (registerDone){
            FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
            Scene scene = new Scene(Loader.load());
            Main.getStage().setScene(scene);
        }
    }
    public void goBackToMainMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
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