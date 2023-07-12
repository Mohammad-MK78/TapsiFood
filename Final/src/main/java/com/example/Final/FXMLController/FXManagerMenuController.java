package com.example.Final.FXMLController;

import com.example.Final.Controller.RestaurantAdminMenuController;
import com.example.Final.Main;
import com.example.Final.Model.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class FXManagerMenuController {
    @FXML
    Label ManagerMenu, showBalance, chargeStatus;
    @FXML
    TextField chargeBalance, restaurantName, restaurantType, restaurantLocation;
    @FXML
    VBox addRestaurantVBox;
    @FXML
    Button addRestaurant, ManagerMenuLogout, ManagerMenuCharge, ManagerMenuShowBalance, showRestaurants, backToManagerMenu, removeRestaurant;
    @FXML
    TableView<Restaurant> myRestaurants, myRestaurantsPrime;
    @FXML
    void initialize(){
        RestaurantAdminMenuController.setCurrentUser();

        TableColumn<Restaurant, String> name = new TableColumn<>("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(100);
        TableColumn<Restaurant, String> type = new TableColumn<>("type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.setPrefWidth(100);
        TableColumn<Restaurant, Double> rate = new TableColumn<>("rating");
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        rate.setPrefWidth(100);
        TableColumn<Restaurant, Integer> address = new TableColumn<>("location");
        address.setCellValueFactory(new PropertyValueFactory<>("location"));
        address.setPrefWidth(100);

        TableColumn<Restaurant, String> name2 = new TableColumn<>("name");
        name2.setCellValueFactory(new PropertyValueFactory<>("name"));
        name2.setPrefWidth(100);
        TableColumn<Restaurant, String> type2 = new TableColumn<>("type");
        type2.setCellValueFactory(new PropertyValueFactory<>("type"));
        type2.setPrefWidth(100);
        TableColumn<Restaurant, Double> rate2 = new TableColumn<>("rating");
        rate2.setCellValueFactory(new PropertyValueFactory<>("rate"));
        rate2.setPrefWidth(100);
        TableColumn<Restaurant, Integer> address2 = new TableColumn<>("location");
        address2.setCellValueFactory(new PropertyValueFactory<>("location"));
        address2.setPrefWidth(100);

        myRestaurants.getColumns().clear();
        myRestaurants.getColumns().addAll(name, type, rate, address);
        myRestaurants.getItems().clear();
        myRestaurants.getItems().addAll();//TODO

        myRestaurantsPrime.getColumns().clear();
        myRestaurantsPrime.getColumns().addAll(name, type, rate, address);
        myRestaurantsPrime.getItems().clear();
        myRestaurantsPrime.getItems().addAll();//TODO

        myRestaurants.setRowFactory(tv -> {
            TableRow<Restaurant> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    FXRestaurantMenuController.restaurant = row.getItem();
                    FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/RestaurantMenu.fxml"));
                    Scene scene;
                    try {
                        scene = new Scene(Loader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Main.getStage().setScene(scene);
                    // TODO
                }
            });
            return row;
        });

        myRestaurantsPrime.setRowFactory(tv -> {
            TableRow<Restaurant> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    // RestaurantAdminMenuController.removeRestaurant();
                    // TODO
                }
            });
            return row;
        });
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