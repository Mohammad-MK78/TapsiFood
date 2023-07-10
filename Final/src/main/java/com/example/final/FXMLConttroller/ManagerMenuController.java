package com.example.Final.FXMLConttroller;

import com.example.Final.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ManagerMenuController {
    @FXML
    Label ManageMenu;
    @FXML
    AnchorPane RestaurantPane;
    @FXML
    Button addRestaurant, ManagerMenuOptions, ManagerMenuLogout, ManagerMenuCharge, ManagerMenuShowBalance, showRestaurants, backToManagerMenu, removeRestaurant;
    @FXML
    TableView myRestaurants, myRestaurantsPrime;
    @FXML
    TableColumn myRestaurantName, myRestaurantType, myRestaurantLocation, myRestaurantRating;
    //ObservableList<Re> t;
    public void addRestaurant() {
        ManageMenu.setVisible(!ManageMenu.isVisible());
        addRestaurant.setVisible(!addRestaurant.isVisible());
        showRestaurants.setVisible(!showRestaurants.isVisible());
        backToManagerMenu.setVisible(!backToManagerMenu.isVisible());
        removeRestaurant.setVisible(!removeRestaurant.isVisible());
        RestaurantPane.setVisible(!RestaurantPane.isVisible());
    }
    public void showRestaurants() {
        ManageMenu.setVisible(!ManageMenu.isVisible());
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
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void goBackToManagerMenu() {
        ManageMenu.setVisible(true);
        addRestaurant.setVisible(true);
        showRestaurants.setVisible(true);
        removeRestaurant.setVisible(true);
        backToManagerMenu.setVisible(false);
        myRestaurants.setVisible(false);
        RestaurantPane.setVisible(false);
        myRestaurantsPrime.setVisible(false);
    }
    public void removeRestaurant() {
        ManageMenu.setVisible(!ManageMenu.isVisible());
        addRestaurant.setVisible(!addRestaurant.isVisible());
        showRestaurants.setVisible(!showRestaurants.isVisible());
        backToManagerMenu.setVisible(!backToManagerMenu.isVisible());
        removeRestaurant.setVisible(!removeRestaurant.isVisible());
        myRestaurantsPrime.setVisible(!myRestaurantsPrime.isVisible());
    }
}