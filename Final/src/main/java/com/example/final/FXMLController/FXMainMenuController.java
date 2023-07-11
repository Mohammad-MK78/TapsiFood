package com.example.Final.FXMLController;

import com.example.Final.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class FXMainMenuController {
    public void goToRegisterMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/RegisterMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void goToLoginMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/LoginMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
}
