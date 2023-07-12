package com.example.Final.FXMLController;

import com.example.Final.Controller.RestaurantAdminMenuController;
import com.example.Final.Controller.RestaurantMenuController;
import com.example.Final.Main;
import com.example.Final.Model.Order;
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

public class FXRestaurantMenuController {
    @FXML
    VBox selectMenuVBox, addFoodVBox, removeFoodVBox, replyVBox;
    @FXML
    Button backToManagerMenu, backToSelectMenu, RestaurantMenuOptions, RestaurantMenuShowBalance, RestaurantMenuChangeType, RestaurantMenuShowType;
    @FXML
    Label showBalanceLabel, changeTypeStatus, showTypeLabel;
    @FXML
    TextField changeTypeTextField;
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
        addFoodVBox.setVisible(!addFoodVBox.isVisible());
        selectMenuVBox.setVisible(!selectMenuVBox.isVisible());
        backToManagerMenu.setVisible(false);
        backToSelectMenu.setVisible(true);
    }
    public void goBackToManagerMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/ManagerMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void goBackToSelectMenu() {
        selectMenuVBox.setVisible(true);
        addFoodVBox.setVisible(false);
        removeFoodVBox.setVisible(false);
        orderHistoryTableView.setVisible(false);
        ongoingOrdersTableView.setVisible(false);
        commentsTableView.setVisible(false);
        replyVBox.setVisible(false);
        backToManagerMenu.setVisible(true);
        backToSelectMenu.setVisible(false);
    }
    public void removeFood() {
        removeFoodVBox.setVisible(!removeFoodVBox.isVisible());
        selectMenuVBox.setVisible(!selectMenuVBox.isVisible());
        backToManagerMenu.setVisible(false);
        backToSelectMenu.setVisible(true);
    }
    public void showOrderHistory() {
        orderHistoryTableView.setVisible(!orderHistoryTableView.isVisible());
        selectMenuVBox.setVisible(!selectMenuVBox.isVisible());
        backToManagerMenu.setVisible(false);
        backToSelectMenu.setVisible(true);
    }
    public void showOngoingOrders() {
        ongoingOrdersTableView.setVisible(!ongoingOrdersTableView.isVisible());
        selectMenuVBox.setVisible(!selectMenuVBox.isVisible());
        backToManagerMenu.setVisible(false);
        backToSelectMenu.setVisible(true);
    }
    public void showComments() {
        commentsTableView.setVisible(!commentsTableView.isVisible());
        selectMenuVBox.setVisible(!selectMenuVBox.isVisible());
        backToManagerMenu.setVisible(false);
        backToSelectMenu.setVisible(true);
    }
    public void submitReply() {

    }
    public void doAddNewFood() {

    }
    public void doRemoveFood() {

    }
    public void goBackToCommentsTableView() {
        commentsTableView.setVisible(true);
        replyVBox.setVisible(false);
    }
    public void showChangeType() {
        changeTypeTextField.setVisible(!changeTypeTextField.isVisible());
        changeTypeTextField.setText("");
        changeTypeStatus.setText("");
    }
    public void changeType(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String result;
            String type = changeTypeTextField.getText();
            result = RestaurantMenuController.changeType(type);
            changeTypeStatus.setText(result);
            changeTypeTextField.setText("");
        }
        updateType();
    }
    public void showType() {
        updateType();
        showTypeLabel.setVisible(!showTypeLabel.isVisible());
    }
    public void updateType() {
        String type = RestaurantMenuController.showType();
        showTypeLabel.setText(type);
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
        RestaurantMenuChangeType.setVisible(!RestaurantMenuChangeType.isVisible());
        RestaurantMenuShowBalance.setVisible(!RestaurantMenuShowBalance.isVisible());
        RestaurantMenuShowType.setVisible(!RestaurantMenuShowType.isVisible());
    }
}