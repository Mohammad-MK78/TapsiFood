package com.example.Final.Controller;

import com.example.Final.Model.*;
import com.example.Final.View.RestaurantAdminMenuEnums;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestaurantAdminMenuController {
    private static RestaurantManager currentUser;

    public static void setCurrentUser() {
        User user = SnappFood.getCurrentUser();
        currentUser = new RestaurantManager(user.getUsername(), user.getPassword(), user.getSecurityQuestion(), user.getCredit());
    }
    public static String addRestaurant(String name, String type, int location) throws SQLException, ClassNotFoundException {

        if(RestaurantAdminMenuEnums.getMatcher(name, RestaurantAdminMenuEnums.VALID_NAME) == null)
            return "add restaurant failed: invalid username format";

        else if(SnappFood.getRestaurantByName(name) != null)
            return "add restaurant failed: name already exists";

        else if(RestaurantAdminMenuEnums.getMatcher(type, RestaurantAdminMenuEnums.VALID_TYPE) == null)
            return "add restaurant failed: invalid type format";
        else if (location < 1 || location > 1000)
            return "add restaurant failed: invalid location format";
        else {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();
            String getManagerID = "SELECT * FROM tapsifood.accounts where username='" + currentUser.getUsername() + "'";
            int managerID = 0;
            ResultSet getID = statement.executeQuery(getManagerID);
            if (getID.next())
                managerID = getID.getInt("id");
            String sql = "INSERT INTO tapsifood.restaurants(name, type, location, managerID) VALUES ('"+name+"', '"+type+"', '"+location+"', '"+managerID+"')";
            statement.executeUpdate(sql);
            currentUser.addRestaurant(new Restaurant(name, type, location, 0));
            return "restaurant added successfully";
        }
    }
    public static ArrayList<Restaurant> getRestaurants() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String getManagerID = "SELECT * FROM tapsifood.accounts where username='" + currentUser.getUsername() + "'";
        int managerID = 0;
        ResultSet getID = statement.executeQuery(getManagerID);
        if (getID.next())
            managerID = getID.getInt("id");
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sqlCheckType = "SELECT * FROM tapsifood.restaurants where managerID='" + managerID + "'";
        ResultSet typeCheck = statement.executeQuery(sqlCheckType);
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        while (typeCheck.next()) {
            String name = typeCheck.getString("name");
            Restaurant restaurant = SnappFood.getRestaurantByName(name);
            restaurants.add(restaurant);
        }
        return restaurants;
    }
    public static void showRestaurants(String command) throws ClassNotFoundException, SQLException {
        Pattern typePattern = Pattern.compile(RestaurantAdminMenuEnums.getString(RestaurantAdminMenuEnums.SHOW_RESTAURANT_OPTION));
        Matcher typeMatcher = typePattern.matcher(command);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String getManagerID = "SELECT * FROM tapsifood.accounts where username='" + currentUser.getUsername() + "'";
        int managerID = 0;
        ResultSet getID = statement.executeQuery(getManagerID);
        if (getID.next())
            managerID = getID.getInt("id");

        if(typeMatcher.find()) {
            String type = typeMatcher.group("type");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sqlCheckType = "SELECT * FROM tapsifood.restaurants where managerID='" + managerID + "' AND type='" + type + "'";
            ResultSet typeCheck = statement.executeQuery(sqlCheckType);
            int index = 1;
            while (typeCheck.next()) {
                String name = typeCheck.getString("name");
                Restaurant restaurant = SnappFood.getRestaurantByName(name);
                System.out.println((index++) + ". " + restaurant.getName() + "-> type: " + restaurant.getType() + " | rating: " + restaurant.getRating() +  " | loc: " + restaurant.getLocation());
            }
        }
        else {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sqlCheckType = "SELECT * FROM tapsifood.restaurants where managerID='" + managerID + "'";
            ResultSet typeCheck = statement.executeQuery(sqlCheckType);
            int index = 1;
            while (typeCheck.next()) {
                String name = typeCheck.getString("name");
                Restaurant restaurant = SnappFood.getRestaurantByName(name);
                System.out.println((index++) + ". " + restaurant.getName() + "-> type: " + restaurant.getType() + " | rating: " + restaurant.getRating() +  " | loc: " + restaurant.getLocation());
            }
        }
    }
    public static String removeRestaurant(Matcher matcher) throws SQLException, ClassNotFoundException {
        String restaurantName = matcher.group("name");

        if(SnappFood.getRestaurantManagerByUsername(restaurantName) != null) {
            SnappFood.removeUser(SnappFood.getRestaurantManagerByUsername(restaurantName));
            return "";
        }

        return "remove restaurant failed: restaurant not found\n";
    }

    public static String chargeAccount(int amount) throws SQLException, ClassNotFoundException {

        if(amount < 1)
            return "Invalid";

        currentUser.changeBalance(amount);
        return "Successful";
    }

    public static int showBalance() {
        return currentUser.getCredit();
    }


    public static String enterRestaurant(Matcher matcher) throws SQLException, ClassNotFoundException {
        String name = matcher.group("name");
        if(SnappFood.getRestaurantByName(name) == null)
            return "enter failed: name not found";
        else {
            SnappFood.setCurrentRestaurant(SnappFood.getRestaurantByName(name));
            return "enter successful";
        }
    }
}