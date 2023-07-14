package com.example.Final.FXMLController;

import com.example.Final.Controller.CustomerMenuController;
import com.example.Final.Controller.SnappFoodAdminMenuController;
import com.example.Final.Main;
import com.example.Final.Model.Cart;
import com.example.Final.Model.Discount;
import com.example.Final.Model.Restaurant;
import com.example.Final.Model.SnappFood;
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

public class FXCustomerMenuController {
    @FXML
    Button backToCustomerMenuSelect, backToRestaurantSelect;
    @FXML
    Label showBalance, chargeStatus, showLocation, changeLocationStatus, CustomerMenu;
    @FXML
    TextField chargeBalance, changeLocationTextField, searchBox;
    @FXML
    VBox searchRestaurantByTypeVBox, totalRestaurantVBox, CustomerRestaurantSelectVBox, customerMenuSelect, discountsVBox, orderHistoryVBox;
    @FXML
    Button customerMenuOptions, customerMenuLogout, customerMenuCharge, customerMenuShowBalance, customerMenuChangeLocation, customerMenuShowLocation;
    @FXML
    TableView<Restaurant> restaurantsByTypeTableView, totalRestaurantsTableView;
    @FXML
    TableView<Discount> discountsTableView;
    @FXML
    TableView<Cart> orderHistoryTableView;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        CustomerMenuController.setCurrentUser();

        TableColumn<Restaurant, String> nameC = new TableColumn<>("name");
        nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameC.setPrefWidth(100);
        TableColumn<Restaurant, String> typeC = new TableColumn<>("type");
        typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeC.setPrefWidth(100);
        TableColumn<Restaurant, Double> rateC = new TableColumn<>("rating");
        rateC.setCellValueFactory(new PropertyValueFactory<>("rate"));
        rateC.setPrefWidth(100);
        TableColumn<Restaurant, Integer> addressC = new TableColumn<>("location");
        addressC.setCellValueFactory(new PropertyValueFactory<>("location"));
        addressC.setPrefWidth(100);

        TableColumn<Restaurant, String> nameC2 = new TableColumn<>("name");
        nameC2.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameC2.setPrefWidth(133);
        TableColumn<Restaurant, Double> rateC2 = new TableColumn<>("rating");
        rateC2.setCellValueFactory(new PropertyValueFactory<>("rate"));
        rateC2.setPrefWidth(133);
        TableColumn<Restaurant, Integer> addressC2 = new TableColumn<>("location");
        addressC2.setCellValueFactory(new PropertyValueFactory<>("location"));
        addressC2.setPrefWidth(133);


        TableColumn<Discount, String> code = new TableColumn<>("code");
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        code.setPrefWidth(200);
        TableColumn<Discount, Integer> discountAmount = new TableColumn<>("discountAmount");
        discountAmount.setCellValueFactory(new PropertyValueFactory<>("discountAmount"));
        discountAmount.setPrefWidth(200);

        discountsTableView.getColumns().clear();
        discountsTableView.getColumns().addAll(code, discountAmount);
        discountsTableView.getItems().clear();
        discountsTableView.getItems().addAll(Discount.getCodes()); // TODO

        totalRestaurantsTableView.getColumns().clear();
        totalRestaurantsTableView.getColumns().addAll(nameC,typeC,rateC,addressC);
        totalRestaurantsTableView.getItems().clear();
        totalRestaurantsTableView.getItems().addAll(SnappFoodAdminMenuController.getRestaurants());

        restaurantsByTypeTableView.getColumns().clear();
        restaurantsByTypeTableView.getColumns().addAll(nameC2,rateC2,addressC2);
        restaurantsByTypeTableView.getItems().clear();
        restaurantsByTypeTableView.getItems().addAll(SnappFoodAdminMenuController.getRestaurantsByType(getSearchBox()));

        totalRestaurantsTableView.setRowFactory(tv -> {
            TableRow<Restaurant> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    FXCustomerRestaurantController.restaurant = row.getItem();
                    FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/CustomerRestaurant.fxml"));
                    Scene scene;
                    try {
                        scene = new Scene(Loader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Main.getStage().setScene(scene);
                }
            });
            return row;
        });
        restaurantsByTypeTableView.setRowFactory(tv -> {
            TableRow<Restaurant> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    FXCustomerRestaurantController.restaurant = row.getItem();
                    FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/CustomerRestaurant.fxml"));
                    Scene scene;
                    try {
                        scene = new Scene(Loader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Main.getStage().setScene(scene);
                }
            });
            return row;
        });

    }
    public String getSearchBox() {
        return searchBox.getText();
    }

    public void showOptions() {
        customerMenuLogout.setVisible(!customerMenuLogout.isVisible());
        customerMenuCharge.setVisible(!customerMenuCharge.isVisible());
        customerMenuShowBalance.setVisible(!customerMenuShowBalance.isVisible());
        customerMenuChangeLocation.setVisible(!customerMenuChangeLocation.isVisible());
        customerMenuShowLocation.setVisible(!customerMenuShowLocation.isVisible());
        if (chargeBalance.isVisible())
            chargeBalance.setVisible(!chargeBalance.isVisible());
        if (showBalance.isVisible())
            showBalance.setVisible(!showBalance.isVisible());
        if (changeLocationTextField.isVisible())
            changeLocationTextField.setVisible(!changeLocationTextField.isVisible());
        if (showLocation.isVisible())
            showLocation.setVisible(!showLocation.isVisible());
    }
    public void logout() throws IOException {
        SnappFood.setCurrentUser(null);
        CustomerMenuController.setCurrentUser();
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
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
                result = "Invalid!";
            else {
                int amount = Integer.parseInt(chargeBalance.getText());
                result = CustomerMenuController.chargeAccount(amount);
            }
            chargeStatus.setText(result);
            chargeBalance.setText("");
        }
        updateBalance();
    }
    public void showBalance() {
        updateBalance();
        showBalance.setVisible(!showBalance.isVisible());
    }
    public void updateBalance() {
        int balance = CustomerMenuController.showBalance();
        showBalance.setText("$" + balance);
    }
    public void showLocation() {
        updateLocation();
        showLocation.setVisible(!showLocation.isVisible());
    }
    public void showChangeLocation() {
        changeLocationTextField.setVisible(!changeLocationTextField.isVisible());
        changeLocationTextField.setText("");
        changeLocationStatus.setText("");
    }
    public void changeLocation(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String result;
            if (!changeLocationTextField.getText().matches("\\d+")) {
                result = "Invalid!";
            }
            else {
                int location = Integer.parseInt(changeLocationTextField.getText());
                result = CustomerMenuController.changeLocation(location); //TODO location is not updating
            }
            changeLocationStatus.setText(result);
            changeLocationTextField.setText("");
        }
        updateLocation();
    }
    public void updateLocation() {
        showLocation.setText(String.valueOf(CustomerMenuController.getLocation()));
    }
    public void selectRestaurant() {
        CustomerRestaurantSelectVBox.setVisible(!CustomerRestaurantSelectVBox.isVisible());
        customerMenuSelect.setVisible(!customerMenuSelect.isVisible());
        backToCustomerMenuSelect.setVisible(true);
        backToRestaurantSelect.setVisible(false);
    }
    public void showOrderHistory() {
        orderHistoryVBox.setVisible(!orderHistoryVBox.isVisible());
        backToCustomerMenuSelect.setVisible(true);
        customerMenuSelect.setVisible(!customerMenuSelect.isVisible());
    }
    public void showDiscounts() {
        discountsVBox.setVisible(!discountsVBox.isVisible());
        backToCustomerMenuSelect.setVisible(true);
        customerMenuSelect.setVisible(!customerMenuSelect.isVisible());
    }
    public void showCart() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/Cart.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void showRestaurants() {
        searchRestaurantByTypeVBox.setVisible(true);
        totalRestaurantVBox.setVisible(false);
        CustomerRestaurantSelectVBox.setVisible(false);
        backToRestaurantSelect.setVisible(true);
        backToCustomerMenuSelect.setVisible(false);
        CustomerMenu.setVisible(false);
    }
    public void totalRestaurants() {
        totalRestaurantVBox.setVisible(true);
        searchRestaurantByTypeVBox.setVisible(false);
        CustomerRestaurantSelectVBox.setVisible(false);
        backToRestaurantSelect.setVisible(true);
        backToCustomerMenuSelect.setVisible(false);
        CustomerMenu.setVisible(false);
    }
    public void goBackToCustomerMenuSelect() {
        CustomerMenu.setVisible(true);
        customerMenuSelect.setVisible(true);
        searchRestaurantByTypeVBox.setVisible(false);
        totalRestaurantVBox.setVisible(false);
        CustomerRestaurantSelectVBox.setVisible(false);
        backToRestaurantSelect.setVisible(false);
        backToCustomerMenuSelect.setVisible(false);
        discountsVBox.setVisible(false);
        orderHistoryVBox.setVisible(false);
    }
    public void goBackToRestaurantSelect() {
        CustomerMenu.setVisible(true);
        searchRestaurantByTypeVBox.setVisible(false);
        totalRestaurantVBox.setVisible(false);
        backToCustomerMenuSelect.setVisible(true);
        backToRestaurantSelect.setVisible(false);
        CustomerRestaurantSelectVBox.setVisible(true);
    }
    public void searchRestaurantsByType() throws SQLException, ClassNotFoundException {
        initialize();
    }
}