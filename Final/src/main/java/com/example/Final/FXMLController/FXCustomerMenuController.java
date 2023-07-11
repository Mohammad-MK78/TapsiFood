package com.example.Final.FXMLController;

import com.example.Final.Controller.CustomerMenuController;
import com.example.Final.Main;
import com.example.Final.Model.Customer;
import com.example.Final.Model.SnappFood;
import com.example.Final.Model.User;
import com.example.Final.View.CustomerMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class FXCustomerMenuController {
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
    @FXML
    void initialize(){
        CustomerMenuController.setCurrentUser();
    }
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
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void showChargeAccount() {
        chargeBalance.setVisible(!chargeBalance.isVisible());
    }
    public void chargeAccount(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            int amount = Integer.parseInt(chargeBalance.getText());
            String result = CustomerMenuController.chargeAccount(amount);
            System.out.println(result);
        }
        updateBalance();
    }
    public void showBalance() {
        updateBalance();
        showBalance.setVisible(!showBalance.isVisible());
    }
    public void updateBalance() {
        int balance = CustomerMenuController.showBalance();
        showBalance.setText(balance + "$");
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