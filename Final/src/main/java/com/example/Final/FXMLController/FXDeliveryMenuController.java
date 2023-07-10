package com.example.Final.FXMLController;

import com.example.Final.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class FXDeliveryMenuController {
    public void showPath() {

    }
    public void showTime() {

    }
    public void showLocation() {

    }
    public void showDestination() {

    }
    public void showRestaurant() {

    }
    public void showOptions() {

    }
    public void showBalance() {

    }
    public void chargeBalance() {

    }
    public void logout() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void backToDeliveryMenu() {

    }
}