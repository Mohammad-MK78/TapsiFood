package Model;

import java.util.ArrayList;

public class Cart {
    ArrayList<Order> orders;
    int rating;
    public Cart(ArrayList<Order> order) {
        orders = order;
    }
}
