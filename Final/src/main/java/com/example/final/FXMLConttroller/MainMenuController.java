package com.example.Final.FXMLConttroller;

import com.example.Final.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MainMenuController {
    public void goToRegisterMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("RegisterMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void goToLoginMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("LoginMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
}
