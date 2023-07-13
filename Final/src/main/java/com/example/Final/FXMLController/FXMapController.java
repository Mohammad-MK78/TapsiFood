package com.example.Final.FXMLController;

import com.example.Final.Controller.CityGraph;
import com.example.Final.Controller.DeliveryMenuController;
import com.example.Final.Controller.ShortestPath;
import com.example.Final.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.util.ArrayList;
import com.example.Final.Model.Node;

public class FXMapController {
    @FXML
    AnchorPane mapPane;
    @FXML
    Group group = new Group();
    @FXML
    void initialize() throws IOException {
        DeliveryMenuController.setDestination(473);
        int start = DeliveryMenuController.showLocation();
        int end = 248; //DeliveryMenuController.showRestaurant();
        int end2 = DeliveryMenuController.showDestination();
        CityGraph cityGraph = new CityGraph();
        int[][] graph = new int[1000][1000];
        for (int i = 0; i < 1000; i++){
            for (int j = 0; j < 1000; j++) {
                graph[i][j] = -1;
            }
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (cityGraph.city.m[i][j] > 0)
                    graph[i][j] = cityGraph.city.m[i][j];
            }
        }
        ShortestPath sp = new ShortestPath(graph);
        System.out.println(sp.findShortestPath(start-1, end-1));
        System.out.println(sp.findShortestPath(end - 1, end2 - 1));
        ArrayList<Integer> pathList = sp.findShortestPath(start-1, end-1);
        ArrayList<Integer> pathList2 = sp.findShortestPath(end - 1, end2 - 1);
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            nodes.add(new Node(i+1,(((i+1)%43)*(1260/43))+10 , (((i+1)/43)*(700/(1000/43)))+10));
        }
        Color color= Color.BLACK;
        for (int i = 0; i < nodes.size(); i++) {
            if (end == i + 1) {
                Circle circle = new Circle();
                circle.setStroke(Color.BLUE);
                circle.setStrokeWidth(10);
                circle.setRadius(3);
                circle.setCenterX(nodes.get(i).x);
                circle.setCenterY(nodes.get(i).y);
                group.getChildren().add(circle);
            }
            if ((start)==i+1) {
                Circle circle = new Circle();
                circle.setStroke(Color.RED);
                circle.setStrokeWidth(10);
                circle.setRadius(3);
                circle.setCenterX(nodes.get(i).x);
                circle.setCenterY(nodes.get(i).y);
                group.getChildren().add(circle);
            }
            if (end2==i+1) {
                Circle circle = new Circle();
                circle.setStroke(Color.GREEN);
                circle.setStrokeWidth(10);
                circle.setRadius(3);
                circle.setCenterX(nodes.get(i).x);
                circle.setCenterY(nodes.get(i).y);
                group.getChildren().add(circle);
            }
            else if (pathList.contains(i+1) && i + 1 != end2 && i+1 != end && i+1 != start){
                Circle circle = new Circle();
                circle.setStroke(Color.RED);
                circle.setStrokeWidth(4);
                circle.setRadius(2);
                circle.setCenterX(nodes.get(i).x);
                circle.setCenterY(nodes.get(i).y);
                group.getChildren().add(circle);
            }
            else if (pathList2.contains(i+1) && i+1 != end2 && i+1 != end && i+1 != start){
                Circle circle = new Circle();
                circle.setStroke(Color.GREEN);
                circle.setStrokeWidth(4);
                circle.setRadius(2);
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
                if (pathList.contains(i+1) && pathList.contains(j+1) && ((pathList.indexOf(i+1)- pathList.indexOf(j+1)==1) || (pathList.indexOf(i+1)- pathList.indexOf(j+1)==-1))){
                    Line line = new Line(nodes.get(i).x, nodes.get(i).y, nodes.get(j).x, nodes.get(j).y);
                    line.setStroke(Color.RED);
                    line.setStrokeWidth(3);
                    group.getChildren().add(line);
                }
                else if (pathList2.contains(i+1) && pathList2.contains(j+1) && ((pathList2.indexOf(i+1)- pathList2.indexOf(j+1)==1) || (pathList2.indexOf(i+1)- pathList2.indexOf(j+1)==-1))){
                    Line line = new Line(nodes.get(i).x, nodes.get(i).y, nodes.get(j).x, nodes.get(j).y);
                    line.setStroke(Color.GREEN);
                    line.setStrokeWidth(3);
                    group.getChildren().add(line);
                }
                if (graph[i][j]!=-1){
                    Line line=new Line(nodes.get(i).x, nodes.get(i).y, nodes.get(j).x, nodes.get(j).y);
                    line.setStroke(color);
                    line.setStrokeWidth(0.1);
                    group.getChildren().add(line);
                }
            }
        }
        mapPane.getChildren().add(group);
    }
    public void goBackToDeliveryMenu() throws IOException {
        mapPane.getChildren().remove(group);
        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("/fxml/DeliveryMenu.fxml"));
        Scene scene = new Scene(Loader.load());
        Main.getStage().setScene(scene);
    }
}