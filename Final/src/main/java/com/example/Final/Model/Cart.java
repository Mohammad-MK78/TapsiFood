package com.example.Final.Model;

import java.util.ArrayList;

public class Cart {
    ArrayList<Order> orders;
    private Delivery delivery;
    private String Foods, customerName;
    private int totalPrice;
    public Cart(ArrayList<Order> order) {
        orders = order;
        delivery = null;
        Foods = "";
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
    public Restaurant getRestaurant() {
        return orders.get(0).getFood().getRestaurant();
    }
    public Customer getCustomer() {
        return orders.get(0).getCustomer();
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void setFoods() {
        for (Order order : this.orders) {
            Foods += order.getFood().getName() + "(" + order.getNumber() + ") ";
        }
    }

    public void setCustomerName() {
        this.customerName = this.getCustomer().getUsername();
    }

    public void showCart() {
        int index = 1;
        int total = 0;
        if (this.getOrders().size() == 0) {
            System.out.println("cart is empty");
            return;
        }
        for(Order order : this.getOrders()) {
            System.out.println(index + ") " + order);
            total += order.getNumber() * order.getFood().getPrice();
            index++;
        }
        System.out.println("Food price : " + total);
        System.out.println("Delivery price : " + total / 5);
        System.out.println("Total : " + (total + total / 5));
        this.totalPrice = total;
        System.out.println("Customer name : " + getCustomer());
    }
}
