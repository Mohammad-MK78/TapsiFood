package com.example.Final.FXMLController;

import com.example.Final.Controller.CustomerMenuController;
import com.example.Final.Controller.SnappFoodAdminMenuController;
import com.example.Final.Main;
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
    Button customerMenuBack;
    @FXML
    Label showBalance, chargeStatus;
    @FXML
    TextField chargeBalance, searchBox;
    @FXML
    VBox searchRestaurantByTypeVBox, totalRestaurantVBox, CustomerMenuVBox;
    @FXML
    Button customerMenuOptions, customerMenuLogout, customerMenuCharge, customerMenuShowBalance;
    @FXML
    TableView<Restaurant> restaurantsByTypeTableView, totalRestaurantsTableView;
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
        if (chargeBalance.isVisible())
            chargeBalance.setVisible(!chargeBalance.isVisible());
        if (showBalance.isVisible())
            showBalance.setVisible(!showBalance.isVisible());
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
                result = "Invalid";
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
    public void showRestaurants() { //TODO رستوراانا باید نشون داده شه برحسب تایپشون
        searchRestaurantByTypeVBox.setVisible(true);
        totalRestaurantVBox.setVisible(false);
        CustomerMenuVBox.setVisible(false);
        customerMenuBack.setVisible(true);
    }
    public void totalRestaurants() { //TODO رستوراانا باید نشون داده شه همشون
        totalRestaurantVBox.setVisible(true);
        searchRestaurantByTypeVBox.setVisible(false);
        CustomerMenuVBox.setVisible(false);
        customerMenuBack.setVisible(true);
    }
    public void goBackToCustomerMenu() {
        searchRestaurantByTypeVBox.setVisible(false);
        totalRestaurantVBox.setVisible(false);
        CustomerMenuVBox.setVisible(true);
        customerMenuBack.setVisible(false);

    }
    public void searchRestaurantsByType() throws SQLException, ClassNotFoundException {
        initialize();
    }
}