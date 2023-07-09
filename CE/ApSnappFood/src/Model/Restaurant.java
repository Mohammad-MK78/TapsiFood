package Model;

import java.sql.*;
import java.util.ArrayList;

public class Restaurant{
    private String name, type;
    private int location, credit;
    private double rating, num;
    private ArrayList<String> comments;
    private ArrayList<Cart> history;
    private ArrayList<String> replies;
    private ArrayList<Food> menu;
    private ArrayList<Food> Starter;
    private ArrayList<Food> MainMeal;
    private ArrayList<Food> Dessert;

    public Restaurant(String name, String type, int location) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.rating = 0;
        this.num = 0;
        this.credit = 0;
        menu = new ArrayList<>();
        Starter = new ArrayList<>();
        MainMeal = new ArrayList<>();
        Dessert = new ArrayList<>();
        comments = new ArrayList<>();
        replies = new ArrayList<>();
    }
    public void addRating(int rate) {
        rating += rate;
        num++;
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

    public double getRating(){
        return rating / num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Food> getStarter() {
        return Starter;
    }

    public ArrayList<Food> getEntree() {
        return MainMeal;
    }

    public ArrayList<Food> getDessert() {
        return Dessert;
    }
    public ArrayList<String> getComments() {
        return comments;
    }

    public void reply(int num, String message) {
        replies.add(num, message);
    }
    public void addFood(Food food) {
        menu.add(food);
        switch (food.getCategory()) {
            case "Starter":
                Starter.add(food);
                break;
            case "MainMeal":
                MainMeal.add(food);
                break;
            case "Dessert":
                Dessert.add(food);
        }
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

    public void removeFood(Food food) {
        menu.remove(food);

        switch (food.getCategory()) {
            case "Starter":
                Starter.remove(food);
                break;
            case "MainMeal":
                MainMeal.remove(food);
                break;
            case "Dessert":
                Dessert.remove(food);
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
