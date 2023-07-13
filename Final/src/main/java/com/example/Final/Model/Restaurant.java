package com.example.Final.Model;

import com.example.Final.Controller.RestaurantMenuController;

import java.sql.*;
import java.util.ArrayList;

public class Restaurant{
    private String name, type;
    private int location, credit;
    private Double rate;
    private ArrayList<Integer> rating;
    private ArrayList<String> comments;
    private ArrayList<Cart> history;
    private ArrayList<Cart> ongoingOrders;
    private ArrayList<String> replies;
    private ArrayList<Food> menu;
    private ArrayList<Food> Starter;
    private ArrayList<Food> MainMeal;
    private ArrayList<Food> Dessert;

    public Restaurant(String name, String type, int location, double rate) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.rate = rate;
        this.rating = new ArrayList<>();
        this.credit = 0;
        ongoingOrders = new ArrayList<>();
        history = new ArrayList<>();
        menu = new ArrayList<>();
        Starter = new ArrayList<>();
        MainMeal = new ArrayList<>();
        Dessert = new ArrayList<>();
        comments = new ArrayList<>();
        replies = new ArrayList<>();
    }

    public void addRating(int rate) {
        rating.add(rate);
    }

    public String getName() {
        return name;
    }

    public int getLocation() {
        return location;
    }

    public int getCredit() {
        return credit;
    }

    public ArrayList<Cart> getHistory() {
        return history;
    }

    public ArrayList<Cart> getOngoingOrders() {
        return ongoingOrders;
    }

    public void addCartToHistory(Cart cart) {
        history.add(cart);
    }
    public void addCartToOngoings(Cart cart) {
        ongoingOrders.add(cart);
    }
    public void removeFromOngoing(Cart cart) {
        ongoingOrders.remove(cart);
    }
    public double getRating(){
        double totalRate = 0;
        if (rating != null) {
            for (int i : rating) {
                totalRate += i;
            }
            return totalRate / rating.size();
        }
        else
            return 0;
    }

    public Double getRate() {
        return rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public ArrayList<Food> getMenu() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String restaurantName = getName();
        String getResID = "SELECT * FROM tapsifood.restaurants where name='" + restaurantName + "'";
        int restaurantID = 0;
        ResultSet getID = statement.executeQuery(getResID);
        if (getID.next())
            restaurantID = getID.getInt("id");
        String sqlCheckFood = "SELECT * FROM tapsifood.foods where restaurantID='" + restaurantID + "'";
        ResultSet nameCheck = statement.executeQuery(sqlCheckFood);
        ArrayList<Food> menu = new ArrayList<>();
        while (nameCheck.next()) {
            Restaurant restaurant = SnappFood.getRestaurantByName(restaurantName);
            String name = nameCheck.getString("name");
            String category = nameCheck.getString("category");
            int price = nameCheck.getInt("price");
            int cost = nameCheck.getInt("cost");
            menu.add(new Food(restaurant, name, category, price, cost));
        }
        return menu;
    }
    public ArrayList<Food> getMainMeal() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String restaurantName = getName();
        String getResID = "SELECT * FROM tapsifood.restaurants where name='" + restaurantName + "'";
        int restaurantID = 0;
        ResultSet getID = statement.executeQuery(getResID);
        if (getID.next())
            restaurantID = getID.getInt("id");
        String sqlCheckFood = "SELECT * FROM tapsifood.foods where restaurantID='" + restaurantID + "' AND category='MainMeal'";
        ResultSet nameCheck = statement.executeQuery(sqlCheckFood);
        ArrayList<Food> mainMeal = new ArrayList<>();
        while (nameCheck.next()) {
            Restaurant restaurant = SnappFood.getRestaurantByName(restaurantName);
            String name = nameCheck.getString("name");
            String category = nameCheck.getString("category");
            int price = nameCheck.getInt("price");
            int cost = nameCheck.getInt("cost");
            mainMeal.add(new Food(restaurant, name, category, price, cost));
        }
        return mainMeal;
    }
    public ArrayList<Food> getStarter() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String restaurantName = getName();
        String getResID = "SELECT * FROM tapsifood.restaurants where name='" + restaurantName + "'";
        int restaurantID = 0;
        ResultSet getID = statement.executeQuery(getResID);
        if (getID.next())
            restaurantID = getID.getInt("id");
        String sqlCheckFood = "SELECT * FROM tapsifood.foods where restaurantID='" + restaurantID + "' AND category='Starter'";
        ResultSet nameCheck = statement.executeQuery(sqlCheckFood);
        ArrayList<Food> starter = new ArrayList<>();
        while (nameCheck.next()) {
            Restaurant restaurant = SnappFood.getRestaurantByName(restaurantName);
            String name = nameCheck.getString("name");
            String category = nameCheck.getString("category");
            int price = nameCheck.getInt("price");
            int cost = nameCheck.getInt("cost");
            starter.add(new Food(restaurant, name, category, price, cost));
        }
        return starter;
    }
    public ArrayList<Food> getDessert() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String restaurantName = getName();
        String getResID = "SELECT * FROM tapsifood.restaurants where name='" + restaurantName + "'";
        int restaurantID = 0;
        ResultSet getID = statement.executeQuery(getResID);
        if (getID.next())
            restaurantID = getID.getInt("id");
        String sqlCheckFood = "SELECT * FROM tapsifood.foods where restaurantID='" + restaurantID + "' AND category='Dessert'";
        ResultSet nameCheck = statement.executeQuery(sqlCheckFood);
        ArrayList<Food> dessert = new ArrayList<>();
        while (nameCheck.next()) {
            Restaurant restaurant = SnappFood.getRestaurantByName(restaurantName);
            String name = nameCheck.getString("name");
            String category = nameCheck.getString("category");
            int price = nameCheck.getInt("price");
            int cost = nameCheck.getInt("cost");
            dessert.add(new Food(restaurant, name, category, price, cost));
        }
        return dessert;
    }
    public ArrayList<String> getComments() {
        return comments; //TODO
    }

    public void reply(int num, String message) {
        replies.add(num, message);
    }
    public void addFood(Food food) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String getManagerID = "SELECT * FROM tapsifood.restaurants where name='" + RestaurantMenuController.getCurrentRestaurant().getName() + "'";
        int restaurantID = 0;
        ResultSet getID = statement.executeQuery(getManagerID);
        if (getID.next())
            restaurantID = getID.getInt("id");
        String sql = "INSERT INTO tapsifood.foods(name, restaurantID, category, price, cost) VALUES ('"+food.getName()+"', '"+restaurantID+"' ,'"+food.getCategory()+"', '"+food.getPrice()+"', '"+food.getCost()+"')";
        statement.executeUpdate(sql);
        menu.add(food);
    }

    public static Food getFoodByName(String name, String restaurantName) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String getResID = "SELECT * FROM tapsifood.restaurants where name='" + restaurantName + "'";
        int restaurantID = 0;
        ResultSet getID = statement.executeQuery(getResID);
        if (getID.next())
            restaurantID = getID.getInt("id");
        String sqlCheckFood = "SELECT * FROM tapsifood.foods where name='" + name + "' AND restaurantID='" + restaurantID + "'";
        ResultSet nameCheck = statement.executeQuery(sqlCheckFood);
        if (nameCheck.next()) {
            Restaurant restaurant = SnappFood.getRestaurantByName(restaurantName);
            String category = nameCheck.getString("category");
            int price = nameCheck.getInt("price");
            int cost = nameCheck.getInt("cost");
            return new Food(restaurant, name, category, price, cost);
        }
        return null;
    }

    public static void removeFood(Food food) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        Restaurant restaurant = food.getRestaurant();
        String restaurantName = restaurant.getName();
        String getResID = "SELECT * FROM tapsifood.restaurants where name='" + restaurantName + "'";
        int restaurantID = 0;
        ResultSet getID = statement.executeQuery(getResID);
        if (getID.next())
            restaurantID = getID.getInt("id");
        String name = food.getName();
        String sqlRegAdmin = "SELECT * FROM tapsifood.foods where name='" + name + "' AND restaurantID= '" + restaurantID + "'";
        ResultSet usernameCheck = statement.executeQuery(sqlRegAdmin);
        if (usernameCheck.next()) {
            String remove = "DELETE FROM tapsifood.foods where name='" + name + "' AND restaurantID= '" + restaurantID + "'";
            statement.executeUpdate(remove);
        }
    }
    public void changeType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return this.getName() + ": type = " + type + " | balance = " + this.getCredit();
    }
    public void changeBalance(int amount) {
        credit += amount;
    }
}
