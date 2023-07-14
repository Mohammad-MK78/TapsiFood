package com.example.Final.FXMLController;

import com.example.Final.Controller.CustomerMenuController;
import com.example.Final.Main;
import com.example.Final.Model.Order;
import com.example.Final.Model.SnappFood;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FXCartController {
    @FXML
    VBox selectMenuVBox, orderStatusVBox, currentCartVBox, purchaseVBox;
    @FXML
    HBox showTimeHBox, showDeliveryHBox;
    @FXML
    Button backToSelectMenu, customerMenuOptions, customerMenuLogout, customerMenuCharge, customerMenuShowBalance, customerMenuChangeLocation, customerMenuShowLocation;
    @FXML
    TextField addDiscountTextField, chargeBalance, changeLocationTextField;
    @FXML
    Label showDeliveryLabel, showTimeLabel, showBalance, chargeStatus, showLocation, changeLocationStatus;
    @FXML
    TableView<Order> currentCartTableView;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        TableColumn<Order, String> name = new TableColumn<>("Food Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(100);

        TableColumn<Order, Integer> number = new TableColumn<>("Number");
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        number.setPrefWidth(100);

        TableColumn<Order, String> price = new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        price.setPrefWidth(100);

        TableColumn<Order, String> totalPrice = new TableColumn<>("Total Price");
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPrice.setPrefWidth(100);


        currentCartTableView.getColumns().clear();
        currentCartTableView.getColumns().addAll(name, number, price, totalPrice);
        currentCartTableView.getItems().clear();
        currentCartTableView.getItems().addAll(CustomerMenuController.getCurrentUser().getCartOrder());
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
    public void openShowCart() throws SQLException, ClassNotFoundException {
        initialize();
        backToSelectMenu.setVisible(true);
        selectMenuVBox.setVisible(false);
        currentCartVBox.setVisible(true);
    }
    public void openShowTime() {
        showTimeLabel.setText(""); //TODO
        backToSelectMenu.setVisible(true);
        selectMenuVBox.setVisible(false);
        showTimeHBox.setVisible(true);
    }
    public void openPurchase() {
        backToSelectMenu.setVisible(true);
        selectMenuVBox.setVisible(false);
        purchaseVBox.setVisible(true);
    }
    public void purchaseCompleted() throws SQLException, ClassNotFoundException {
        String discount = addDiscountTextField.getText();
        String result = CustomerMenuController.purchaseCart(discount);
        System.out.println(result);
    }
    public void openShowDelivery() {
        if (CustomerMenuController.getCurrentUser().getCurrentCart().getDelivery() == null)
            showDeliveryLabel.setText("no delivery");
        else
            showDeliveryLabel.setText(CustomerMenuController.getCurrentUser().getCurrentCart().getDelivery().getUsername());
        backToSelectMenu.setVisible(true);
        selectMenuVBox.setVisible(false);
        showDeliveryHBox.setVisible(true);
    }
    public void openOrderStatus() {
        backToSelectMenu.setVisible(true);
        selectMenuVBox.setVisible(false);
        orderStatusVBox.setVisible(true);
    }
    public void orderNotCollected() {
        orderStatusVBox.setVisible(false);
        selectMenuVBox.setVisible(true);
        backToSelectMenu.setVisible(false);
    }
    public void orderCollected() throws SQLException, ClassNotFoundException {
        orderStatusVBox.setVisible(false);
        selectMenuVBox.setVisible(true);
        backToSelectMenu.setVisible(false);
        CustomerMenuController.collected();
    }
    public void goBackToSelectMenu() {
        backToSelectMenu.setVisible(false);
        currentCartVBox.setVisible(false);
        showTimeHBox.setVisible(false);
        showDeliveryHBox.setVisible(false);
        orderStatusVBox.setVisible(false);
        purchaseVBox.setVisible(false);
        selectMenuVBox.setVisible(true);
    }
    public void goBackToCustomerMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/CustomerMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
}