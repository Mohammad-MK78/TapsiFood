package com.example.Final.Controller;

import com.example.Final.Model.*;
import com.example.Final.View.LoginMenuEnums;
import com.example.Final.View.SnappFoodAdminMenuEnums;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnappFoodAdminMenuController {

    public static String restaurantManagerRegister(Matcher matcher) throws ClassNotFoundException, SQLException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String security_question = matcher.group("securityQuestion");

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
    public static ArrayList<Restaurant> getRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("khar", "FastFood", 25, 0));
        restaurants.add(new Restaurant("khar2", "Kababi", 36, 0));
        restaurants.add(new Restaurant("khar3", "jigaraki", 78, 0));
        return restaurants;
    }
    public static ArrayList<Restaurant> getRestaurantsByType(String searchBox) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("khar", "FastFood", 25, 0));
        restaurants.add(new Restaurant("khar2", "Kababi", 36, 0));
        restaurants.add(new Restaurant("khar3", "jigaraki", 78, 0));
        ArrayList<Restaurant> result = new ArrayList<>();
        for (Restaurant restaurant : restaurants)
            if (restaurant.getType().toLowerCase().equals(searchBox))
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

    public static String addDiscount(Matcher matcher) throws SQLException, ClassNotFoundException {
        String customerUsername = matcher.group("username");
        int discountAmount = Integer.parseInt(matcher.group("amount"));
        String code = matcher.group("code");

        if(SnappFood.getCustomerByUsername(customerUsername) == null)
            return "set discount failed: username not found";

        else if(discountAmount < 1)
            return "set discount failed: invalid amount";

        else if(SnappFoodAdminMenuEnums.getMatcher(code, SnappFoodAdminMenuEnums.VALID_DISCOUNT_CODE) == null)
            return "set discount failed: invalid code format";

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
