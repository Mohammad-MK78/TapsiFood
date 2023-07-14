package com.example.Final.Model;

import java.sql.*;
import java.util.ArrayList;

public class Cart {
    ArrayList<Order> cart;
    private Delivery delivery;
    private int time;
    private String Foods, customerName;
    private int totalPrice;
    public Cart(ArrayList<Order> cart) {
        this.cart = cart;
        delivery = null;
        Foods = "";
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void addToCart(Order order) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String foodName = order.getFood().getName();
        String restaurantName = order.getFood().getRestaurant().getName();

        String getRestaurantID = "SELECT * FROM tapsifood.restaurants where name='" + restaurantName + "'";
        int restaurantID = 0;
        ResultSet getID = statement.executeQuery(getRestaurantID);
        if (getID.next())
            restaurantID = getID.getInt("id");

        String sqlOrderID = "SELECT * FROM tapsifood.orders where foodName='" + foodName + "' AND restaurantID = '"+ restaurantID +"'";
        ResultSet OrderIDCheck = statement.executeQuery(sqlOrderID);
        int orderID = 0;
        if (OrderIDCheck.next())
            orderID = OrderIDCheck.getInt("id");

        String sqlCustomerID = "SELECT * FROM tapsifood.orders where foodName='" + foodName + "' AND restaurantID = '"+ restaurantID +"'";
        ResultSet customerIDCheck = statement.executeQuery(sqlCustomerID);
        int customerID = 0, cardNumber = 0;
        if (customerIDCheck.next()) {
            customerID = customerIDCheck.getInt("id");
            cardNumber = customerIDCheck.getInt("numOfCards");
        }

        String addToCart = "INSERT INTO tapsifood.cart(orderID, customerID, cardNumber) VALUES ('"+orderID+"', '"+customerID+"', '"+cardNumber+"')";
        statement.executeUpdate(addToCart);

        cart.add(order);
    }
    public void removeFromCart(Order order) {
        cart.remove(order);
    }
    public void resetCart() {
        this.cart.clear();
        this.delivery = null;
    }
    public ArrayList<Order> getCart() {
        return cart;
    }
    public Delivery getDelivery() {
        return delivery;
    }
    public Restaurant getRestaurant() {
        return cart.get(0).getFood().getRestaurant();
    }
    public Customer getCustomer() {
        return cart.get(0).getCustomer();
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void setFoods() {
        for (Order order : this.cart) {
            Foods += order.getFood().getName() + "(" + order.getNumber() + ") ";
        }
    }


    public void showCart() {
        int index = 1;
        int total = 0;
        if (this.getCart().size() == 0) {
            System.out.println("cart is empty");
            return;
        }
        for(Order order : this.getCart()) {
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
