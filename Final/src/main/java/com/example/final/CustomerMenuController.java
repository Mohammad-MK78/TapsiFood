package com.example.Final;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CustomerMenuController {
    @FXML
    TableView restaurantsByType = new TableView<>(), totalRestaurants = new TableView<>();
    @FXML
    Button customerMenuBack;
    @FXML
    Label showBalance;
    @FXML
    TextField chargeBalance, searchBox;
    @FXML
    VBox searchRestaurantByTypeVBox, totalRestaurantVBox, CustomerMenuVBox;
    @FXML
    Button customerMenuOptions, customerMenuLogout, customerMenuCharge, customerMenuShowBalance;
    public void showRestaurantsByType() {
    }
    public void showOptions() {
        customerMenuLogout.setVisible(!customerMenuLogout.isVisible());
        customerMenuCharge.setVisible(!customerMenuCharge.isVisible());
        customerMenuShowBalance.setVisible(!customerMenuShowBalance.isVisible());
        if (chargeBalance.isVisible())
            chargeBalance.setVisible(!chargeBalance.isVisible());
        if (showBalance.isVisible())
            showBalance.setVisible(!showBalance.isVisible());
    }
    public void logout() throws IOException {
        FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        HelloApplication.getStage().setScene(scene);
    }
    public void showChargeAccount() {
        chargeBalance.setVisible(!chargeBalance.isVisible());
    }
    public void showBalance() {
        showBalance.setVisible(!showBalance.isVisible());
    }
    public void showRestaurants() {
        searchRestaurantByTypeVBox.setVisible(true);
        totalRestaurantVBox.setVisible(false);
        CustomerMenuVBox.setVisible(false);
        customerMenuBack.setVisible(true);
        //restaurantsByType.setUserData(new Button());
    }
    public void totalRestaurants() {
        totalRestaurantVBox.setVisible(true);
        searchRestaurantByTypeVBox.setVisible(false);
        CustomerMenuVBox.setVisible(false);
        customerMenuBack.setVisible(true);
    }
    public void goBackToCustomerMenu() {
        searchRestaurantByTypeVBox.setVisible(false);
        totalRestaurantVBox.setVisible(false);
        CustomerMenuVBox.setVisible(true);
        customerMenuBack.setVisible(false);
    }
}