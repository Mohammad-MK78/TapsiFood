package com.example.Final.FXMLController;

import com.example.Final.Controller.CustomerMenuController;
import com.example.Final.Controller.SnappFoodAdminMenuController;
import com.example.Final.Main;
import com.example.Final.Model.Discount;
import com.example.Final.Model.Restaurant;
import com.example.Final.Model.SnappFood;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class FXSnappFoodAdminMenuController {
    @FXML
    Button adminMenuLogout, backToAdminMenu;
    @FXML
    Label SnappFoodAdminMenu, registerResultLabel;
    @FXML
    TextField usernameInp, securityQuestionInp, selectManagerName, discountCodeTextField, discountAmountTextField, discountUsernameTextField;
    @FXML
    PasswordField passwordInp;
    @FXML
    VBox selectVBox, adminRemoveManagerVBox, totalDiscountsVBox, totalRestaurantVBox;
    @FXML
    HBox adminAddManagerHBox, setDiscountHBox;
    @FXML
    TableView<Restaurant> totalRestaurantsTableView;
    @FXML
    TableView<Discount> totalDiscountsTableView;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

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

        totalRestaurantsTableView.getColumns().clear();
        totalRestaurantsTableView.getColumns().addAll(nameC,typeC,rateC,addressC);
        totalRestaurantsTableView.getItems().clear();
        totalRestaurantsTableView.getItems().addAll(SnappFoodAdminMenuController.getRestaurants());

        TableColumn<Discount, String> code = new TableColumn<>("code");
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        code.setPrefWidth(133);
        TableColumn<Discount, Double> amount = new TableColumn<>("amount");
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amount.setPrefWidth(133);
        TableColumn<Discount, String> customerName = new TableColumn<>("username");
        customerName.setCellValueFactory(new PropertyValueFactory<>("username"));
        customerName.setPrefWidth(133);

        totalDiscountsTableView.getColumns().clear();
        totalDiscountsTableView.getColumns().addAll(code,amount,customerName);
        totalDiscountsTableView.getItems().clear();
        totalDiscountsTableView.getItems().addAll(); //TODO show all discounts
    }
    public void showOptions() {
        adminMenuLogout.setVisible(!adminMenuLogout.isVisible());
    }
    public void logout() throws IOException {
        SnappFood.setCurrentUser(null);
        CustomerMenuController.setCurrentUser();
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void addManager() {
        adminAddManagerHBox.setVisible(true);
        backToAdminMenu.setVisible(true);
        selectVBox.setVisible(false);
    }
    public void removeManager() {
        adminRemoveManagerVBox.setVisible(true);
        backToAdminMenu.setVisible(true);
        selectVBox.setVisible(false);
    }
    public void removeManagerCompleted() {
        // TODO
        adminRemoveManagerVBox.setVisible(false);
        selectVBox.setVisible(true);
        backToAdminMenu.setVisible(false);
    }
    public void showRestaurants() {
        totalRestaurantVBox.setVisible(true);
        backToAdminMenu.setVisible(true);
        selectVBox.setVisible(false);
    }
    public void setDiscount() {
        setDiscountHBox.setVisible(true);
        backToAdminMenu.setVisible(true);
        selectVBox.setVisible(false);
    }
    public void setDiscountCompleted() throws SQLException, ClassNotFoundException {
        String customerUsername = discountUsernameTextField.getText();
        String code = discountCodeTextField.getText();
        int amount = Integer.parseInt(discountAmountTextField.getText());
        String result = SnappFoodAdminMenuController.addDiscount(customerUsername, code, amount);
        System.out.println(result);
        setDiscountHBox.setVisible(false);
        backToAdminMenu.setVisible(false);
        selectVBox.setVisible(true);
    }
    public void showDiscounts() {
        totalDiscountsVBox.setVisible(true);
        backToAdminMenu.setVisible(true);
        selectVBox.setVisible(false);
    }
    public void goBackToAdminMenu() {
        selectVBox.setVisible(true);
        adminAddManagerHBox.setVisible(false);
        adminRemoveManagerVBox.setVisible(false);
        totalRestaurantVBox.setVisible(false);
        totalDiscountsVBox.setVisible(false);
        setDiscountHBox.setVisible(false);
        backToAdminMenu.setVisible(false);
    }
    public void managerRegister() throws SQLException, ClassNotFoundException {
        String username = usernameInp.getText();
        String password = passwordInp.getText();
        String securityQuestion = securityQuestionInp.getText();
        String result = SnappFoodAdminMenuController.restaurantManagerRegister(username, password, securityQuestion);
        registerResultLabel.setText(result);
        registerResultLabel.setVisible(true);
    }
}
