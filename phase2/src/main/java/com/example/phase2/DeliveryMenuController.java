package com.example.phase2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class DeliveryMenuController {
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
        FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        HelloApplication.getStage().setScene(scene);
    }
    public void backToDeliveryMenu() {

    }
}