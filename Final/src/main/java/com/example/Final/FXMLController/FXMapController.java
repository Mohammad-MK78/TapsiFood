package com.example.Final.FXMLController;

import com.example.Final.Controller.DeliveryMenuController;
import com.example.Final.Main;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.util.ArrayList;
import com.example.Final.Model.Node;

public class FXMapController {
    @FXML
    void initialize() throws IOException {
        DeliveryMenuController.setDestination(473);
        Group group = new Group();
        int start = DeliveryMenuController.showLocation();
        int end = 456;
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
    }
}