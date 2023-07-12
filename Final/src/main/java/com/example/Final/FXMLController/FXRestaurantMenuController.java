package com.example.Final.FXMLController;

import com.example.Final.Controller.RestaurantAdminMenuController;
import com.example.Final.Model.Order;
import com.example.Final.Model.Restaurant;
import com.example.Final.Model.SnappFood;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class FXRestaurantMenuController {
    @FXML
    VBox selectMenuVBox, addFoodVBox, removerFoodVBox, replyVBox;
    @FXML
    Button backToManagerMenu, RestaurantMenuOptions, RestaurantMenuShowBalance, RestaurantMenuChangeType;
    @FXML
    Label showBalanceLabel;
    @FXML
    TableView<Order> orderHistoryTableView, ongoingOrdersTableView;
    @FXML
    TableView<String> commentsTableView;

    public static Restaurant restaurant;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        SnappFood.setCurrentRestaurant(SnappFood.getRestaurantByName(restaurant.getName()));

        TableColumn<Order, String> Foods = new TableColumn<>("Foods");
        Foods.setCellValueFactory(new PropertyValueFactory<>("Foods"));
        Foods.setPrefWidth(200);
        TableColumn<Order, String> customerName = new TableColumn<>("customerName");
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerName.setPrefWidth(100);
        TableColumn<Order, Integer> totalPrice = new TableColumn<>("totalPrice");
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPrice.setPrefWidth(100);
    }

    public void addFood() {

    }
    public void goBackToManagerMenu() {

    }
    public void goBackToSelectMenu() {

    }
    public void removeFood() {

    }
    public void showOrderHistory() {

    }
    public void showOngoingOrders() {

    }
    public void showComments() {

    }
    public void submitReply() {

    }
    public void doAddNewFood() {

    }
    public void doRemoveFood() {

    }
    public void goBackToCommentsTableView() {

    }
    public void changeType() {

    }
    public void updateBalance() {
        int balance = RestaurantAdminMenuController.showBalance();
        showBalanceLabel.setText("$" + balance);
    }
    public void showBalance() {
        updateBalance();
        showBalanceLabel.setVisible(!showBalanceLabel.isVisible());
    }
    public void showOptions() {

    }
}