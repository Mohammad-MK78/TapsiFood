package com.example.Final.Model;

import java.sql.*;
import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<Discount> discounts;
    private ArrayList<Cart> carts;
    private Cart currentCart;
    private int debt;

    public Customer(String username, String password, int location, String security_question, int credit, int debt) {
        super(username, password, location, "customer",security_question, credit, debt, 0);
        discounts = new ArrayList<>();
        currentCart = new Cart(new ArrayList<>());
        carts = new ArrayList<>();
        this.debt = debt;
    }

    public Delivery getDelivery() {
        return currentCart.getDelivery();
    }
    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public Discount getDiscountByCode(String code) {
        for(Discount discount : discounts)
            if(discount.getCode().equals(code))
                return discount;
        return null;
    }

    public void setCurrentCart() throws SQLException, ClassNotFoundException {
        this.currentCart = new Cart(getCartOrder());
        int totlaP = 0;
        for (Order order : getCartOrder()){
            totlaP += order.getTotalPrice();
        }
        currentCart.setTotalPrice(totlaP);
    }

    public Cart getCurrentCart() {
        return currentCart;
    }
    public ArrayList<Order> getCartOrder() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        Statement statement2 = connection.createStatement();

        String getCustomerID = "SELECT * FROM tapsifood.accounts where username='" + getUsername() + "'";
        int customerID = 0;
        ResultSet getCID = statement.executeQuery(getCustomerID);
        if (getCID.next())
            customerID = getCID.getInt("id");


        String sqlCheckOrders = "SELECT * FROM tapsifood.orders where customerID='" + customerID + "'";
        ResultSet orderCheck = statement.executeQuery(sqlCheckOrders);
        ArrayList<Order> orders = new ArrayList<>();
        while (orderCheck.next()) {
            int restaurantID = orderCheck.getInt("restaurantID");
            String getRestaurantID = "SELECT * FROM tapsifood.restaurants where id='" + restaurantID + "'";
            String restaurantName = "";
            ResultSet getRID = statement2.executeQuery(getRestaurantID);
            if (getRID.next())
                restaurantName = getRID.getString("name");
            String foodName = orderCheck.getString("foodName");
            Food food = Restaurant.getFoodByName(foodName, restaurantName);
            int number = orderCheck.getInt("number");
            Customer customer = this;
            Order order = new Order(food, number, customer);
            orders.add(order);
        }
        return orders;
    }
    public ArrayList<Cart> getCarts() {
        return carts;
    }
    public void addCart(Cart cart) throws ClassNotFoundException, SQLException {
        this.carts.add(cart);
    }

    public void addToCart(Order order) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        Food food = order.getFood();
        String foodName = food.getName();
        String restaurantName = food.getRestaurant().getName();

        String getRestaurantID = "SELECT * FROM tapsifood.restaurants where name='" + restaurantName + "'";
        int restaurantID = 0;
        ResultSet getRID = statement.executeQuery(getRestaurantID);
        if (getRID.next())
            restaurantID = getRID.getInt("id");

        String getFoodID = "SELECT * FROM tapsifood.foods where name='" + foodName + "'";
        int foodID = 0;
        ResultSet getFID = statement.executeQuery(getFoodID);
        if (getFID.next())
            foodID = getFID.getInt("id");

        String getCustomerID = "SELECT * FROM tapsifood.accounts where username='" + order.getCustomer().getUsername() + "'";
        int customerID = 0;
        ResultSet getCID = statement.executeQuery(getCustomerID);
        if (getCID.next())
            customerID = getCID.getInt("id");

        String getOrderID = "SELECT * FROM tapsifood.orders where restaurantID='"+restaurantID+"' AND foodID='"+foodID+"' AND customerID='"+customerID+"'";
        ResultSet getOID = statement.executeQuery(getOrderID);
        int orderID = 0, orderNumber = 0;
        boolean orderExist = false;
        if (getOID.next()) {
            orderExist = true;
            orderID = getOID.getInt("id");
            orderNumber = getOID.getInt("number");
        }

        if (orderExist) {
            String change = "UPDATE tapsifood.orders SET number='"+(orderNumber+1)+"' WHERE id='" + orderID + "'";
            statement.executeUpdate(change);
        }
        else {
            String addOrder = "INSERT INTO tapsifood.orders(restaurantID, foodID, foodName, customerID, price) VALUES ('"+restaurantID+"', '"+foodID+"', '"+foodName+"', '"+ customerID +"', '"+food.getPrice()+"')";
            statement.executeUpdate(addOrder);
            currentCart.addToCart(order);
        }
    }

    public Order getOrderByFood(Food food) {
        for(Order order : currentCart.getCart())
            if(order.getFood().equals(food))
                return order;
        return null;
    }

    public Order getOrderByFoodNameAndRestaurantName(String foodName, String restaurantName) {
        for(Order order : currentCart.getCart())
            if(order.getFood().getName().equals(foodName) &&
                order.getFood().getRestaurant().getName().equals(restaurantName))
                return order;
        return null;
    }

    public void removeOrder(Order order) {
        currentCart.removeFromCart(order);
    }

    public int getDebt() {
        return debt;
    }

    public void changeDebt(int amount) {
        this.debt += amount;
    }

    public void removeDiscount(Discount discount) {
        this.discounts.remove(discount);
    }

    public void resetCart() {
        this.currentCart.resetCart();
    }
    public void addComment(String message, String restaurantName) throws SQLException, ClassNotFoundException {
        SnappFood.getRestaurantByName(restaurantName).getComments().add(message + " : " + this.getUsername());
    }
    public void addRating(int rate, int orderNumber) {
        this.carts.get(orderNumber-1).getRestaurant().addRating(rate);
    }

    public void finishPurchase(Cart currentCart) throws SQLException, ClassNotFoundException {
        addCart(currentCart);
//        setCurrentCart();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
        String username = currentCart.getCustomer().getUsername();

        String getCustomerID = "SELECT * FROM tapsifood.accounts where username='" + username + "'";
        int customerID = 0;
        ResultSet getCID = statement.executeQuery(getCustomerID);
        if (getCID.next())
            customerID = getCID.getInt("id");

        String statusSQL = "SELECT * FROM tapsifood.cart where customerID='"+customerID+"' AND status='0'";
        ResultSet getStat = statement2.executeQuery(statusSQL);
        while (getStat.next()) {
            String change = "UPDATE tapsifood.cart SET status='1' where customerID='"+customerID+"' AND status='0'";
            statement.executeUpdate(change);
        }

        String orders = "SELECT * FROM tapsifood.orders where customerID='" + customerID + "'";
        ResultSet getOrders = statement3.executeQuery(orders);
        while (getOrders.next()) {
            String remove = "DELETE FROM tapsifood.orders WHERE customerID='" + customerID +"'";
            statement.executeUpdate(remove);
        }

        String numOfCartSQL = "SELECT * FROM tapsifood.accounts where id='"+customerID+"'";
        ResultSet getNumOfCart = statement2.executeQuery(numOfCartSQL);
        if (getNumOfCart.next()) {
            int oldNum = getNumOfCart.getInt("numOfCarts");
            String change = "UPDATE tapsifood.accounts SET numOfCarts='"+(oldNum+1)+"' where id='"+customerID+"'";
            statement.executeUpdate(change);
        }

        getCartOrder();
    }
}