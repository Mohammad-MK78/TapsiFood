package com.example.Final.FXMLController;

import com.example.Final.Controller.CustomerMenuController;
import com.example.Final.Main;
import com.example.Final.Model.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class FXCartController {
    @FXML
    VBox selectMenuVBox, orderStatusVBox, currentCartVBox, purchaseVBox;
    @FXML
    HBox showTimeHBox, showDeliveryHBox;
    @FXML
    Button backToSelectMenu;
    @FXML
    TextField addDiscountTextField;
    @FXML
    Label showDeliveryLabel, showTimeLabel;
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
        currentCartTableView.getItems().addAll(CustomerMenuController.getCurrentUser().getCart());
        System.out.println(CustomerMenuController.getCurrentUser().getCurrentCart().getCart());
    }
    public void openShowCart() {
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
        CustomerMenuController.purchaseCart(discount);
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