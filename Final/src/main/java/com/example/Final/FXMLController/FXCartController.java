package com.example.Final.FXMLController;

import com.example.Final.Main;
import com.example.Final.Model.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FXCartController {
    @FXML
    VBox selectMenuVBox, orderStatusVBox, currentCartVBox, purchaseVBox;
    @FXML
    HBox showTimeHBox, showDeliveryHBox;
    @FXML
    Button backToSelectMenu;
    @FXML
    TableView<Order> currentCartTableView;
    public void openShowCart() {
        backToSelectMenu.setVisible(true);
        selectMenuVBox.setVisible(false);
        currentCartVBox.setVisible(true);
    }
    public void openShowTime() {
        backToSelectMenu.setVisible(true);
        selectMenuVBox.setVisible(false);
        showTimeHBox.setVisible(true);
    }
    public void openPurchase() {
        backToSelectMenu.setVisible(true);
        selectMenuVBox.setVisible(false);
        purchaseVBox.setVisible(true);
    }
    public void openShowDelivery() {
        backToSelectMenu.setVisible(true);
        selectMenuVBox.setVisible(false);
        showDeliveryHBox.setVisible(true);
    }
    public void openOrderStatus() {
        backToSelectMenu.setVisible(true);
        selectMenuVBox.setVisible(false);
        orderStatusVBox.setVisible(true);
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