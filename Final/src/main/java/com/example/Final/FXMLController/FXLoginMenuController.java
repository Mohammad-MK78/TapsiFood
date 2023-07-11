package com.example.Final.FXMLController;

import com.example.Final.Controller.LoginMenuController;
import com.example.Final.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

public class FXLoginMenuController {
    @FXML
    Hyperlink forgotPassword;
    @FXML
    Label yourPassword, securityLabel, wrongSecurityQuestion, notAppropriatePassword;
    @FXML
    TextField usernameInp, securityQuestionInp, recoverPasswordInp;
    @FXML
    PasswordField passwordInp;
    public void exit() {
        Platform.exit();
    }
    public void showSecurityQuestionForForgottenPassword() {
        forgotPassword.setVisible(false);
        securityLabel.setVisible(true);
        securityQuestionInp.setVisible(true);
    }
    public void forgotPasswordWithEnter(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
            forgotPassword();
    }
    public void forgotPassword() throws SQLException, ClassNotFoundException {
        boolean securityQuestionTrue = false;
        String username = usernameInp.getText();
        String securityQuestion = securityQuestionInp.getText();
        String securityQuestionResult = LoginMenuController.forgotPassword(username, securityQuestion);
        if (securityQuestionResult.equals("securityQuestion is ok"))
            securityQuestionTrue = true;
        if (securityQuestionTrue){
            yourPassword.setVisible(true);
            recoverPasswordInp.setVisible(true);
            wrongSecurityQuestion.setVisible(false);
        }
        else
            wrongSecurityQuestion.setVisible(true);
    }
    public void setNewPasswordWithEnter(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String username = usernameInp.getText();
            String newPassword = recoverPasswordInp.getText();
            String changePassResult = LoginMenuController.setNewPassword(username, newPassword);
            System.out.println(changePassResult);
        }
    }
    public void loginWithEnterKey(KeyEvent keyEvent) throws IOException, SQLException, ClassNotFoundException {
        if (keyEvent.getCode() == KeyCode.ENTER)
            loginComplete();
    }
    public void loginComplete() throws IOException, SQLException, ClassNotFoundException {
        boolean loginDone = false;
        String username = usernameInp.getText();
        String password = passwordInp.getText();
        String result = LoginMenuController.login(username, password);
        if (result.equals("admin") || result.equals("manager") || result.equals("customer") || result.equals("delivery"))
            loginDone = true;
        if (loginDone){
            FXMLLoader loader;
            Scene scene;
            switch (result){
                case "admin":
                    loader = new FXMLLoader(Main.class.getResource("/fxml/AdminMenu.fxml"));
                    scene = new Scene(loader.load());
                    Main.getStage().setScene(scene);
                    break;
                case "manager":
                    loader = new FXMLLoader(Main.class.getResource("/fxml/ManagerMenu.fxml"));
                    scene = new Scene(loader.load());
                    Main.getStage().setScene(scene);
                    break;
                case "customer":
                    loader = new FXMLLoader(Main.class.getResource("/fxml/CustomerMenu.fxml"));
                    scene = new Scene(loader.load());
                    Main.getStage().setScene(scene);
                    break;
                case "delivery":
                    loader = new FXMLLoader(Main.class.getResource("/fxml/DeliveryMenu.fxml"));
                    scene = new Scene(loader.load());
                    Main.getStage().setScene(scene);
                    break;
            }
        }
    }
    public void goBackToMainMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
}