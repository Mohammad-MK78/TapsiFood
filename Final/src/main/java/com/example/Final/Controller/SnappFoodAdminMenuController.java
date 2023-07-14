package com.example.Final.Controller;

import com.example.Final.Model.*;
import com.example.Final.View.LoginMenuEnums;
import com.example.Final.View.SnappFoodAdminMenuEnums;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnappFoodAdminMenuController {

    public static String restaurantManagerRegister(String username, String password, String security_question) throws ClassNotFoundException, SQLException {

        if(LoginMenuEnums.getMatcher(username, LoginMenuEnums.VALID_USERNAME) == null)
            return "Register failed: invalid username format";

        else if(SnappFood.getUserByUsername(username) != null)
            return "Register failed: username already exists";

        else if(LoginMenuEnums.getMatcher(password, LoginMenuEnums.VALID_PASSWORD) == null)
            return "Register failed: invalid password format";

        else if(password.length() < 5 ||
                !Pattern.compile("[a-z]").matcher(password).find() ||
                !Pattern.compile("[A-Z]").matcher(password).find() ||
                !Pattern.compile("\\d").matcher(password).find())
            return "Register failed: weak password";
        else {
            String sql = "INSERT INTO tapsifood.accounts(username, password, position, security_question) VALUES ('"+username+"', '"+password+"', 'manager', '"+security_question+"')";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            SnappFood.addRestaurantManager(new RestaurantManager(username, password, security_question, 0));
            return "Manager Register successful";
        }
    }
    public static String removeRestaurantManager(Matcher matcher) throws SQLException, ClassNotFoundException {
        String username = matcher.group("username");

        if(SnappFood.getUserByUsername(username) == null)
            return "remove account failed: username not found";

        else {
            SnappFood.removeUser(SnappFood.getUserByUsername(username));
            return "remove account successful";
        }
    }
    public static ArrayList<Restaurant> getRestaurants() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlCheckRestaurant = "SELECT * FROM tapsifood.restaurants";
        ResultSet restaurantCheck = statement.executeQuery(sqlCheckRestaurant);
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        while (restaurantCheck.next()) {
            String name = restaurantCheck.getString("name");
            restaurants.add(SnappFood.getRestaurantByName(name));
        }
        return restaurants;
    }
    public static ArrayList<Restaurant> getRestaurantsByType(String type) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlCheckType = "SELECT * FROM tapsifood.restaurants where type='" + type + "'";
        ResultSet typeCheck = statement.executeQuery(sqlCheckType);
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        while (typeCheck.next()) {
            String name = typeCheck.getString("name");
            restaurants.add(SnappFood.getRestaurantByName(name));
        }
        ArrayList<Restaurant> result = new ArrayList<>();
        for (Restaurant restaurant : restaurants)
            if (type.toLowerCase().equals(restaurant.getType()))
                result.add(restaurant);
        return result;
    }
    public static void showRestaurant(String command) throws ClassNotFoundException, SQLException {
        Pattern typePattern = Pattern.compile(SnappFoodAdminMenuEnums.getString(SnappFoodAdminMenuEnums.SHOW_RESTAURANT_OPTION));
        Matcher typeMatcher = typePattern.matcher(command);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();

        if(typeMatcher.find()) {
            String type = typeMatcher.group("type");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sqlCheckType = "SELECT * FROM tapsifood.restaurants where type='" + type + "'";
            ResultSet typeCheck = statement.executeQuery(sqlCheckType);
            int index = 1, managerID;
            while (typeCheck.next()) {
                managerID = typeCheck.getInt("managerID");
                String sqlManagerType = "SELECT * FROM tapsifood.accounts where id='" + managerID + "'";
                Statement statement2 = connection.createStatement();
                ResultSet managerCheck = statement2.executeQuery(sqlManagerType);
                String managerName = "";
                if (managerCheck.next())
                    managerName = managerCheck.getString("username");
                String name = typeCheck.getString("name");
                Restaurant restaurant = SnappFood.getRestaurantByName(name);
                System.out.println((index++) + ". " + restaurant.getName() + " | Manager: " + managerName + " -> type: " + restaurant.getType() + " | rating: " + restaurant.getRating() +  " | loc: " + restaurant.getLocation());
            }
        }
        else {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sqlCheckRestaurant = "SELECT * FROM tapsifood.restaurants";
            ResultSet restaurantCheck = statement.executeQuery(sqlCheckRestaurant);
            int index = 1, managerID;
            while (restaurantCheck.next()) {
                String name = restaurantCheck.getString("name");
                managerID = restaurantCheck.getInt("managerID");
                String sqlManagerType = "SELECT * FROM tapsifood.accounts where id='" + managerID + "'";
                Statement statement2 = connection.createStatement();
                ResultSet managerCheck = statement2.executeQuery(sqlManagerType);
                String managerName = "";
                if (managerCheck.next())
                    managerName = managerCheck.getString("username");
                Restaurant restaurant = SnappFood.getRestaurantByName(name);
                System.out.println((index++) + ". " + restaurant.getName() + " | Manager: " + managerName + " -> type: " + restaurant.getType() + " | rating: " + restaurant.getRating() +  " | loc: " + restaurant.getLocation());
            }
        }
    }

    public static String addDiscount(String customerUsername, String code, int discountAmount) throws SQLException, ClassNotFoundException {

        if(SnappFood.getCustomerByUsername(customerUsername) == null)
            return "set discount failed: username not found";

        else if(discountAmount < 1)
            return "set discount failed: invalid amount";

        else {
            SnappFood.addDiscount(new Discount(SnappFood.getCustomerByUsername(customerUsername), discountAmount, code));
            return "discount set successfully";
        }
    }

    public static void showDiscounts() {
        int index = 1;

        for(Discount discount : SnappFood.getDiscounts()) {
            System.out.println(index + ") " + discount.getCode() + " | amount=" + discount.getDiscountAmount() + " --> user=" + discount.getDiscountUser().getUsername());
            index++;
        }
    }
}
