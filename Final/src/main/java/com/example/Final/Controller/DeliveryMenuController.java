package com.example.Final.Controller;

import com.example.Final.Model.Delivery;
import com.example.Final.Model.SnappFood;
import com.example.Final.Model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryMenuController {
    private static Delivery currentUser;
    public static void setCurrentUser() {
        User user = SnappFood.getCurrentUser();
        currentUser = new Delivery(user.getUsername(), user.getPassword(), user.getLocation(), user.getSecurityQuestion(), user.getCredit(), user.getIs_busy());
    }
    public static void setDestination(int destination) {
        currentUser.setDestination(destination);
    }
    public static String showDestination(){
        return String.valueOf(currentUser.getDestination());
    }
    public static String showLocation() {
        return String.valueOf(currentUser.getLocation());
    }
    public static String showRestaurant() {
        return String.valueOf(currentUser.getLocation());
    }
    public static int showBalance() {
        return currentUser.getCredit();
    }
    public static String chargeAccount(int amount) throws SQLException, ClassNotFoundException {

        if(amount < 1)
            return "Invalid";

        currentUser.changeBalance(amount);
        return "Successful";
    }
    public static int[][] getCityGraph() throws IOException {
        CityGraph cityGraph = new CityGraph();
        int[][] graph = new int[1001][1001];
        for(int i = 0; i < cityGraph.city.rows; i++) {
            if (cityGraph.city.cols >= 0)
                System.arraycopy(cityGraph.city.m[i], 0, graph[i], 0, cityGraph.city.cols);
        }
        return graph;
    }
    public static String show_distance() throws IOException {
        int location = currentUser.getLocation(), destination = currentUser.getDestination(), restaurant = currentUser.getLocation();
        ShortestPath shortestPath = new ShortestPath(getCityGraph());
        int distance = shortestPath.shortestPath(location, restaurant) + shortestPath.shortestPath(restaurant, destination);
        return (distance + "mins");
    }
    public static ArrayList<Integer> show_path(int location, int destination) throws IOException {
        CityGraph cityGraph = new CityGraph();
        int[][] graph = new int[1001][1001];
        for(int i = 0; i < cityGraph.city.rows; i++) {
            if (cityGraph.city.cols >= 0)
                System.arraycopy(cityGraph.city.m[i], 0, graph[i], 0, cityGraph.city.cols);
        }
        ShortestPath sp = new ShortestPath(graph);
        //System.out.println("path : " + sp.findShortestPath(location, destination).toString());
        return sp.findShortestPath(location, destination);
    }
}
