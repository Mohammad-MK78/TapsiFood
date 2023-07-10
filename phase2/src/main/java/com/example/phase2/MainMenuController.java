package com.example.phase2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MainMenuController {
    public void goToRegisterMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("RegisterMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        HelloApplication.getStage().setScene(scene);
    }
    public void goToLoginMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("LoginMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        HelloApplication.getStage().setScene(scene);
    }
}
