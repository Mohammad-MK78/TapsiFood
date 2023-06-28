package Model;

import java.io.IOException;
import java.util.ArrayList;

public class Delivery extends User{
    private int location, destination;
    private Order order;
    public Delivery(String username, String password) {
        super(username, password);
    }
    public void setLocation(int location) {
        this.location = location;
    }
    public int getLocation() {
        return this.location;
    }
    public void setDestination(int destination) {
        this.destination = destination;
    }
    public int getDestination() {
        return this.destination;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Order getOrder() {
        return this.order;
    }
    public void show_path(int location, int destination) throws IOException {
        CityGraph cityGraph = new CityGraph();
        int[][] graph = new int[1001][1001];
        for(int i = 0; i < cityGraph.city.rows; i++) {
            if (cityGraph.city.cols >= 0) System.arraycopy(cityGraph.city.m[i], 0, graph[i], 0, cityGraph.city.cols);
        }
        ShortestPath gfg = new ShortestPath(graph);
        ArrayList<Integer> path = gfg.findShortestPath(location, destination);
        int distance = gfg.shortestPath(location, destination);
        System.out.println(distance);
        System.out.println(path.toString());
    }
}