package com.example.Final.FXMLController;

import com.example.Final.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FXManagerMenuController {
    @FXML
    Label ManagerMenu;
    @FXML
    VBox addRestaurantVBox;
    @FXML
    Button addRestaurant, ManagerMenuLogout, ManagerMenuCharge, ManagerMenuShowBalance, showRestaurants, backToManagerMenu, removeRestaurant;
    @FXML
    TableView myRestaurants, myRestaurantsPrime;
    //ObservableList<Re> t;
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
        ManagerMenuCharge.setVisible(!ManagerMenuCharge.isVisible());
        ManagerMenuLogout.setVisible(!ManagerMenuLogout.isVisible());
        ManagerMenuShowBalance.setVisible(!ManagerMenuShowBalance.isVisible());
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
}