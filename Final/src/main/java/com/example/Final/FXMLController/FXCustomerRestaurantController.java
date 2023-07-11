package com.example.Final.FXMLController;

import com.example.Final.Main;
import com.example.Final.Model.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FXCustomerRestaurantController {
    @FXML
    VBox selectMenuVBox, totalMenuVbox, starterMenuVbox, mainMealMenuVbox, dessertMenuVbox, selectFoodVBox;
    @FXML
    Button backToCustomerMenu, backToMenuSelect;

    public static Restaurant restaurant;
    public void openTotalMenu() {
        selectFoodVBox.setVisible(false);
        backToCustomerMenu.setVisible(false);
        backToMenuSelect.setVisible(true);
        totalMenuVbox.setVisible(true);
    }
    public void openStarterMenu() {
        selectFoodVBox.setVisible(false);
        backToCustomerMenu.setVisible(false);
        backToMenuSelect.setVisible(true);
        starterMenuVbox.setVisible(true);
    }
    public void openMainMealMenu() {
        selectFoodVBox.setVisible(false);
        backToCustomerMenu.setVisible(false);
        backToMenuSelect.setVisible(true);
        mainMealMenuVbox.setVisible(true);
    }
    public void openDessertMenu() {
        selectFoodVBox.setVisible(false);
        backToCustomerMenu.setVisible(false);
        backToMenuSelect.setVisible(true);
        dessertMenuVbox.setVisible(true);
    }
    public void goBackToMenuSelect() {
        totalMenuVbox.setVisible(false);
        starterMenuVbox.setVisible(false);
        mainMealMenuVbox.setVisible(false);
        dessertMenuVbox.setVisible(false);
        selectMenuVBox.setVisible(true);
    }
    public void goBackToCustomerMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/CustomerMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void goBackToSelectFood() {
        selectFoodVBox.setVisible(false);
    }
    public void foodSelected() {
        selectFoodVBox.setVisible(true);
    }
}