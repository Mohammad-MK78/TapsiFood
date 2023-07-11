package com.example.Final.Controller;

import com.example.Final.Model.Delivery;
import com.example.Final.Model.SnappFood;
import com.example.Final.Model.User;

import java.io.IOException;
import java.util.ArrayList;

public class DeliveryMenuController {
    private static Delivery currentUser;
    public static void setCurrentUser() {
        User user = SnappFood.getCurrentUser();
        currentUser = new Delivery(user.getUsername(), user.getPassword(), user.getLocation(), user.getSecurityQuestion(), user.getCredit(), user.getIs_busy());
    }
    public static void showRestaurant() {
        System.out.println("restaurant location : " + currentUser.getRestaurant());
    }
    public static void showDestination() {
        System.out.println("destination : " + currentUser.getDestination());
    }
    public static void showLocation() {
        System.out.println("location : " + currentUser.getLocation());
    }
    public static void show_distance() throws IOException {
        int location = currentUser.getRestaurant(), destination = currentUser.getDestination();
        CityGraph cityGraph = new CityGraph();
        int[][] graph = new int[1001][1001];
        for(int i = 0; i < cityGraph.city.rows; i++) {
            if (cityGraph.city.cols >= 0)
                System.arraycopy(cityGraph.city.m[i], 0, graph[i], 0, cityGraph.city.cols);
        }
        ShortestPath gfg = new ShortestPath(graph);
        int distance = gfg.shortestPath(location, destination);
        System.out.println("distance : " + distance);
    }
    public static void show_path() throws IOException {
        int location = currentUser.getRestaurant(), destination = currentUser.getDestination();
        CityGraph cityGraph = new CityGraph();
        int[][] graph = new int[1001][1001];
        for(int i = 0; i < cityGraph.city.rows; i++) {
            if (cityGraph.city.cols >= 0)
                System.arraycopy(cityGraph.city.m[i], 0, graph[i], 0, cityGraph.city.cols);
        }
        ShortestPath sp = new ShortestPath(graph);
        ArrayList<Integer> path = sp.findShortestPath(location, destination);
        System.out.println("path : " + path.toString());
    }
}
