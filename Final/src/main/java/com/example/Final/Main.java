package com.example.Final;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage = stage;
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/Pictures/Logo.png")));
        stage.setTitle("TapsiFood!");
        stage.setScene(scene);
        stage.show();
    }
    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}