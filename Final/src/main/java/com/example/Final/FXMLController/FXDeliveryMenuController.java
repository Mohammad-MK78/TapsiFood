package com.example.Final.FXMLController;


import com.example.Final.Controller.DeliveryMenuController;
import com.example.Final.Controller.SnappFoodAdminMenuController;
import com.example.Final.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class FXDeliveryMenuController {
    @FXML
    Label showBalance, chargeStatus;
    @FXML
    TextField chargeBalance;
    @FXML
    VBox selectItem;
    @FXML
    Button DeliveryMenuOptions, DeliveryMenuLogout, DeliveryMenuCharge, DeliveryMenuShowBalance, backToDeliveryMenu;

    @FXML
    void initialize(){
        DeliveryMenuController.setCurrentUser();
    }
    public void showPath() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/Map.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
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
        DeliveryMenuLogout.setVisible(!DeliveryMenuLogout.isVisible());
        DeliveryMenuCharge.setVisible(!DeliveryMenuCharge.isVisible());
        DeliveryMenuShowBalance.setVisible(!DeliveryMenuShowBalance.isVisible());
        if (chargeBalance.isVisible())
            chargeBalance.setVisible(!chargeBalance.isVisible());
        if (showBalance.isVisible())
            showBalance.setVisible(!showBalance.isVisible());
    }
    public void showBalance() {
        updateBalance();
        showBalance.setVisible(!showBalance.isVisible());
    }
    public void chargeBalance() {
        chargeBalance.setVisible(!chargeBalance.isVisible());
        chargeBalance.setText("");
        chargeStatus.setText("");
    }
    public void chargeAccount(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String result;
            if (!chargeBalance.getText().matches("\\d+"))
                result = "Invalid";
            else {
                int amount = Integer.parseInt(chargeBalance.getText());
                result = DeliveryMenuController.chargeAccount(amount);
            }
            chargeStatus.setText(result);
            chargeBalance.setText("");
        }
        updateBalance();
    }
    public void logout() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void updateBalance() {
        int balance = DeliveryMenuController.showBalance();
        showBalance.setText("$" + balance);
    }
    public void backToDeliveryMenu() {

    }
}