package com.example.Final.FXMLController;


import com.example.Final.Controller.DeliveryMenuController;
import com.example.Final.Main;
import com.example.Final.Model.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class FXDeliveryMenuController {
    @FXML
    Label showBalance, chargeStatus;
    @FXML
    TextField chargeBalance;
    @FXML
    VBox selectItem;
    @FXML
    Button DeliveryMenuOptions, DeliveryMenuLogout, DeliveryMenuCharge, DeliveryMenuShowBalance, backToDeliveryMenu;
    @FXML
    void initialize() {
        DeliveryMenuController.setCurrentUser();
    }
    public void showPath() throws IOException {
        DeliveryMenuController.setDestination(473);
        int start = DeliveryMenuController.showLocation();
        int end = 456;
        Group group = new Group();
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            nodes.add(new Node(i + 1,(((i+1)%43)*(1260/43))+10 , (((i+1)/43)*(700/(1000/43)))+10));
        }
        Color color= Color.BLACK;
        for (int i = 0; i < nodes.size(); i++) {
            if ((start-1)==i || (end-1)==i) {
                Circle circle = new Circle();
                circle.setStroke(Color.RED);
                circle.setStrokeWidth(10);
                circle.setRadius(3);
                circle.setCenterX(nodes.get(i).x);
                circle.setCenterY(nodes.get(i).y);
                group.getChildren().add(circle);

            }
            else if (DeliveryMenuController.show_path(start, end).contains(i+1)){
                Circle circle = new Circle();
                circle.setStroke(Color.RED);
                circle.setStrokeWidth(4);
                circle.setRadius(2.5);
                circle.setCenterX(nodes.get(i).x);
                circle.setCenterY(nodes.get(i).y);
                group.getChildren().add(circle);
            }
            else{
                Circle circle = new Circle();
                circle.setStroke(color);
                circle.setStrokeWidth(2);
                circle.setRadius(2);
                circle.setCenterX(nodes.get(i).x);
                circle.setCenterY(nodes.get(i).y);
                group.getChildren().add(circle);
            }
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (DeliveryMenuController.show_path(start, end).contains(i+1) && DeliveryMenuController.show_path(start, end).contains(j+1) && ((DeliveryMenuController.show_path(start, end).indexOf(i+1)-DeliveryMenuController.show_path(start, end).indexOf(j+1)==1) || (DeliveryMenuController.show_path(start, end).indexOf(i+1)-DeliveryMenuController.show_path(start, end).indexOf(j+1)==-1))){
                    Line line=new Line(nodes.get(i).x, nodes.get(i).y, nodes.get(j).x, nodes.get(j).y);
                    line.setStroke(Color.RED);
                    line.setStrokeWidth(3);
                    group.getChildren().add(line);
                }
                if (DeliveryMenuController.getCityGraph()[i][j]!=-1){
                    Line line=new Line(nodes.get(i).x, nodes.get(i).y, nodes.get(j).x, nodes.get(j).y);
                    line.setStroke(color);
                    line.setStrokeWidth(0.08);
                    group.getChildren().add(line);
                }
            }
        }
        Scene scene = new Scene(group, 1280, 720);
        Main.getStage().setScene(scene);
    }
    public void showTime() {

    }
    public void showLocation() {

    }
    public void showDestination() {

    }
    public void showRestaurant() {

    }
    public void showOptions() {
        DeliveryMenuLogout.setVisible(!DeliveryMenuLogout.isVisible());
        DeliveryMenuCharge.setVisible(!DeliveryMenuCharge.isVisible());
        DeliveryMenuShowBalance.setVisible(!DeliveryMenuShowBalance.isVisible());
        if (chargeBalance.isVisible())
            chargeBalance.setVisible(!chargeBalance.isVisible());
        if (showBalance.isVisible())
            showBalance.setVisible(!showBalance.isVisible());
    }
    public void showBalance() {
        updateBalance();
        showBalance.setVisible(!showBalance.isVisible());
    }
    public void chargeBalance() {
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
                result = DeliveryMenuController.chargeAccount(amount);
            }
            chargeStatus.setText(result);
            chargeBalance.setText("");
        }
        updateBalance();
    }
    public void logout() throws IOException {
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
    public void updateBalance() {
        int balance = DeliveryMenuController.showBalance();
        showBalance.setText("$" + balance);
    }
    public void backToDeliveryMenu() {

    }
}