package com.example.Final.FXMLController;

import com.example.Final.Controller.CustomerMenuController;
import com.example.Final.Main;
import com.example.Final.Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class FXCustomerRestaurantController {
    @FXML
    VBox selectMenuVBox, totalMenuVbox, starterMenuVbox, mainMealMenuVbox, dessertMenuVbox;
    @FXML
    Button backToCustomerMenu, backToMenuSelect;
    @FXML
    Label restaurantName;
    @FXML
    TableView<Food> dessertMenuTableView, mainMealMenuTableView, starterMenuTableView, totalMenuTableView;

    public static Restaurant restaurant;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        SnappFood.setCurrentRestaurant(SnappFood.getRestaurantByName(restaurant.getName()));
        restaurantName.setText(restaurant.getName());

        TableColumn<Food, String> name = new TableColumn<>("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(133);
        TableColumn<Food, String> category = new TableColumn<>("category");
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        category.setPrefWidth(133);
        TableColumn<Food, String> price = new TableColumn<>("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        price.setPrefWidth(133);

        TableColumn<Food, String> starterName = new TableColumn<>("name");
        starterName.setCellValueFactory(new PropertyValueFactory<>("name"));
        starterName.setPrefWidth(200);
        TableColumn<Food, String> starterPrice = new TableColumn<>("price");
        starterPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        starterPrice.setPrefWidth(200);

        TableColumn<Food, String> mainMealName = new TableColumn<>("name");
        mainMealName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainMealName.setPrefWidth(200);
        TableColumn<Food, String> mainMealPrice = new TableColumn<>("price");
        mainMealPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        mainMealPrice.setPrefWidth(200);

        TableColumn<Food, String> dessertName = new TableColumn<>("name");
        dessertName.setCellValueFactory(new PropertyValueFactory<>("name"));
        dessertName.setPrefWidth(200);
        TableColumn<Food, String> dessertPrice = new TableColumn<>("price");
        dessertPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        dessertPrice.setPrefWidth(200);

        totalMenuTableView.getColumns().clear();
        totalMenuTableView.getColumns().addAll(name, category, price);
        totalMenuTableView.getItems().clear();
        totalMenuTableView.getItems().addAll(restaurant.getMenu());

        starterMenuTableView.getColumns().clear();
        starterMenuTableView.getColumns().addAll(starterName, starterPrice);
        starterMenuTableView.getItems().clear();
        starterMenuTableView.getItems().addAll(restaurant.getStarter());

        mainMealMenuTableView.getColumns().clear();
        mainMealMenuTableView.getColumns().addAll(mainMealName, mainMealPrice);
        mainMealMenuTableView.getItems().clear();
        mainMealMenuTableView.getItems().addAll(restaurant.getMainMeal());

        dessertMenuTableView.getColumns().clear();
        dessertMenuTableView.getColumns().addAll(dessertName, dessertPrice);
        dessertMenuTableView.getItems().clear();
        dessertMenuTableView.getItems().addAll(restaurant.getDessert());

        totalMenuTableView.setRowFactory(tv -> {
            TableRow<Food> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    CustomerMenuController.getCurrentUser().addToCart(new Order(row.getItem(), 1, CustomerMenuController.getCurrentUser()));
                    System.out.println(CustomerMenuController.getCurrentUser().getCurrentCart().getOrders());
                }
            });
            return row;
        });

        starterMenuTableView.setRowFactory(tv -> {
            TableRow<Food> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    CustomerMenuController.getCurrentUser().addToCart(new Order(row.getItem(), 1, CustomerMenuController.getCurrentUser()));
                    System.out.println(CustomerMenuController.getCurrentUser().getCurrentCart().getOrders());
                }
            });
            return row;
        });

        mainMealMenuTableView.setRowFactory(tv -> {
            TableRow<Food> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    CustomerMenuController.getCurrentUser().addToCart(new Order(row.getItem(), 1, CustomerMenuController.getCurrentUser()));
                    System.out.println(CustomerMenuController.getCurrentUser().getCurrentCart().getOrders());
                }
            });
            return row;
        });

        dessertMenuTableView.setRowFactory(tv -> {
            TableRow<Food> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    CustomerMenuController.getCurrentUser().addToCart(new Order(row.getItem(), 1, CustomerMenuController.getCurrentUser()));
                    System.out.println(CustomerMenuController.getCurrentUser().getCurrentCart().getOrders());
                }
            });
            return row;
        });

    }
    public void openTotalMenu() throws SQLException, ClassNotFoundException {
        selectMenuVBox.setVisible(false);
        backToCustomerMenu.setVisible(false);
        backToMenuSelect.setVisible(true);
        totalMenuVbox.setVisible(true);
        initialize();
    }
    public void openStarterMenu() throws SQLException, ClassNotFoundException {
        selectMenuVBox.setVisible(false);
        backToCustomerMenu.setVisible(false);
        backToMenuSelect.setVisible(true);
        starterMenuVbox.setVisible(true);
        initialize();
    }
    public void openMainMealMenu() throws SQLException, ClassNotFoundException {
        selectMenuVBox.setVisible(false);
        backToCustomerMenu.setVisible(false);
        backToMenuSelect.setVisible(true);
        mainMealMenuVbox.setVisible(true);
        initialize();
    }
    public void openDessertMenu() throws SQLException, ClassNotFoundException {
        selectMenuVBox.setVisible(false);
        backToCustomerMenu.setVisible(false);
        backToMenuSelect.setVisible(true);
        dessertMenuVbox.setVisible(true);
        initialize();
    }
    public void goBackToMenuSelect() {
        totalMenuVbox.setVisible(false);
        starterMenuVbox.setVisible(false);
        mainMealMenuVbox.setVisible(false);
        dessertMenuVbox.setVisible(false);
        selectMenuVBox.setVisible(true);
        backToCustomerMenu.setVisible(true);
        backToMenuSelect.setVisible(false);
    }
    public void goBackToCustomerMenu() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/CustomerMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }

}