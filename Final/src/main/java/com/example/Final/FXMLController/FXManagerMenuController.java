package com.example.Final.FXMLController;

import com.example.Final.Controller.CustomerMenuController;
import com.example.Final.Controller.RestaurantAdminMenuController;
import com.example.Final.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class FXManagerMenuController {
    @FXML
    Label ManagerMenu, showBalance, chargeStatus;
    @FXML
    TextField chargeBalance;
    @FXML
    VBox addRestaurantVBox;
    @FXML
    Button addRestaurant, ManagerMenuLogout, ManagerMenuCharge, ManagerMenuShowBalance, showRestaurants, backToManagerMenu, removeRestaurant;
    @FXML
    TableView myRestaurants, myRestaurantsPrime;
    //ObservableList<Re> t;
    @FXML
    void initialize(){
        RestaurantAdminMenuController.setCurrentUser();
    }
    public void addRestaurant() {
        ManagerMenu.setVisible(!ManagerMenu.isVisible());
        addRestaurant.setVisible(!addRestaurant.isVisible());
        showRestaurants.setVisible(!showRestaurants.isVisible());
        backToManagerMenu.setVisible(!backToManagerMenu.isVisible());
        removeRestaurant.setVisible(!removeRestaurant.isVisible());
        addRestaurantVBox.setVisible(!addRestaurantVBox.isVisible());
    }
    public void showRestaurants() {
        ManagerMenu.setVisible(!ManagerMenu.isVisible());
        addRestaurant.setVisible(!addRestaurant.isVisible());
        showRestaurants.setVisible(!showRestaurants.isVisible());
        backToManagerMenu.setVisible(!backToManagerMenu.isVisible());
        removeRestaurant.setVisible(!removeRestaurant.isVisible());
        myRestaurants.setVisible(!myRestaurants.isVisible());
        myRestaurants.setTableMenuButtonVisible(true);
    }
    public void showOptions() {
        ManagerMenuLogout.setVisible(!ManagerMenuLogout.isVisible());
        ManagerMenuCharge.setVisible(!ManagerMenuCharge.isVisible());
        ManagerMenuShowBalance.setVisible(!ManagerMenuShowBalance.isVisible());
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
    public void goBackToManagerMenu() {
        ManagerMenu.setVisible(true);
        addRestaurant.setVisible(true);
        showRestaurants.setVisible(true);
        removeRestaurant.setVisible(true);
        backToManagerMenu.setVisible(false);
        myRestaurants.setVisible(false);
        addRestaurantVBox.setVisible(false);
        myRestaurantsPrime.setVisible(false);
    }
    public void removeRestaurant() {
        ManagerMenu.setVisible(!ManagerMenu.isVisible());
        addRestaurant.setVisible(!addRestaurant.isVisible());
        showRestaurants.setVisible(!showRestaurants.isVisible());
        backToManagerMenu.setVisible(!backToManagerMenu.isVisible());
        removeRestaurant.setVisible(!removeRestaurant.isVisible());
        myRestaurantsPrime.setVisible(!myRestaurantsPrime.isVisible());
    }
    public void showChargeAccount() {
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
                result = RestaurantAdminMenuController.chargeAccount(amount);
            }
            chargeStatus.setText(result);
            chargeBalance.setText("");
        }
        updateBalance();
    }
    public void updateBalance() {
        int balance = RestaurantAdminMenuController.showBalance();
        showBalance.setText("$" + balance);
    }
    public void showBalance() {
        updateBalance();
        showBalance.setVisible(!showBalance.isVisible());
    }
}