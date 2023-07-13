package com.example.Final.FXMLController;

import com.example.Final.Controller.RestaurantAdminMenuController;
import com.example.Final.Controller.RestaurantMenuController;
import com.example.Final.Main;
import com.example.Final.Model.Comment;
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
    Button backToManagerMenu, backToSelectMenu, RestaurantMenuOptions, RestaurantMenuShowBalance, RestaurantMenuChangeType, RestaurantMenuShowType, addNewFood;
    @FXML
    Label showBalanceLabel, changeTypeStatus, showTypeLabel, restaurantName;
    @FXML
    TextField changeTypeTextField, foodName, foodCategory, foodCost, foodPrice, foodName2;
    @FXML
    TableView<Order> orderHistoryTableView, ongoingOrdersTableView;
    @FXML
    TableView<Comment> commentsTableView; //TODO create class Comment :..)

    public static Restaurant restaurant;

    public static void setRestaurant(Restaurant restaurant) {
        FXRestaurantMenuController.restaurant = restaurant;
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        SnappFood.setCurrentRestaurant(SnappFood.getRestaurantByName(restaurant.getName()));
        RestaurantMenuController.setCurrentRestaurant();
        restaurantName.setText(RestaurantMenuController.getCurrentRestaurant().getName());

        TableColumn<Order, String> Foods = new TableColumn<>("Foods");
        Foods.setCellValueFactory(new PropertyValueFactory<>("Foods"));
        Foods.setPrefWidth(200);
        TableColumn<Order, String> customerName = new TableColumn<>("customerName");
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerName.setPrefWidth(100);
        TableColumn<Order, Integer> totalPrice = new TableColumn<>("totalPrice");
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPrice.setPrefWidth(100);

        TableColumn<Order, String> Foods2 = new TableColumn<>("Foods");
        Foods2.setCellValueFactory(new PropertyValueFactory<>("Foods"));
        Foods2.setPrefWidth(200);
        TableColumn<Order, String> customerName2 = new TableColumn<>("customerName");
        customerName2.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerName2.setPrefWidth(100);
        TableColumn<Order, Integer> totalPrice2 = new TableColumn<>("totalPrice");
        totalPrice2.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPrice2.setPrefWidth(100);

        TableColumn<Comment, String> comment = new TableColumn<>("comment");
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        comment.setPrefWidth(150);
        TableColumn<Comment, String> reply = new TableColumn<>("reply");
        reply.setCellValueFactory(new PropertyValueFactory<>("reply"));
        reply.setPrefWidth(150);
        TableColumn<Comment, String> cusName = new TableColumn<>("username");
        cusName.setCellValueFactory(new PropertyValueFactory<>("username"));
        cusName.setPrefWidth(100);

        commentsTableView.getColumns().clear();
        commentsTableView.getColumns().addAll(comment, reply, cusName);
        commentsTableView.getItems().clear();
        commentsTableView.getItems().addAll(); // TODO

        orderHistoryTableView.getColumns().clear();
        orderHistoryTableView.getColumns().addAll(Foods, customerName, totalPrice);
        orderHistoryTableView.getItems().clear();
        orderHistoryTableView.getItems().addAll(); // TODO

        ongoingOrdersTableView.getColumns().clear();
        ongoingOrdersTableView.getColumns().addAll(Foods2, customerName2, totalPrice2);
        ongoingOrdersTableView.getItems().clear();
        ongoingOrdersTableView.getItems().addAll(); // TODO

    }

    public void addFood() {
        addFoodVBox.setVisible(!addFoodVBox.isVisible());
        selectMenuVBox.setVisible(!selectMenuVBox.isVisible());
        backToManagerMenu.setVisible(false);
        backToSelectMenu.setVisible(true);
    }
    public void addFoodCompleted() throws SQLException, ClassNotFoundException {
        String name = foodName.getText();
        String category = foodCategory.getText();
        int price = Integer.parseInt(foodPrice.getText());
        int cost = Integer.parseInt(foodCost.getText());
        String result = RestaurantMenuController.addFood(name, category, price, cost);
        System.out.println(result);
        initialize();
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
    public void removeFoodCompleted() throws SQLException, ClassNotFoundException {
        String name = foodName2.getText();
        String result = RestaurantMenuController.removeFood(name);
        System.out.println(result);
        initialize();
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
        // TODO
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