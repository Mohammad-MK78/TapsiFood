package Model;

import java.util.ArrayList;

public class Cart {
    ArrayList<Order> orders;
    int rating;
    private Delivery delivery;
    public Cart(ArrayList<Order> order) {
        orders = order;
        delivery = null;
    }
    public void addToCart(Order order) {
        orders.add(order);
    }
    public void removeOrder(Order order) {
        orders.remove(order);
    }
    public void resetCart() {
        this.orders.clear();
        this.delivery = null;
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }
    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
