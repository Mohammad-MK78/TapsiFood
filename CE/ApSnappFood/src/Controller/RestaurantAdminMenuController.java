package Controller;

import Model.*;
import View.RestaurantAdminMenuEnums;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestaurantAdminMenuController {
    private static RestaurantManager currentUser;

    public static void setCurrentUser() {
        User user = SnappFood.getCurrentUser();
        currentUser = new RestaurantManager(user.getUsername(), user.getPassword(), user.getSecurity_question());
    }
    public static String addRestaurant(Matcher matcher) throws SQLException, ClassNotFoundException {
        String name = matcher.group("name");
        String type = matcher.group("type");
        int location = Integer.parseInt(matcher.group("location"));

        if(RestaurantAdminMenuEnums.getMatcher(name, RestaurantAdminMenuEnums.VALID_NAME) == null)
            return "add restaurant failed: invalid username format";

        else if(SnappFood.getUserByUsername(name) != null)
            return "add restaurant failed: username already exists";

        else if(RestaurantAdminMenuEnums.getMatcher(type, RestaurantAdminMenuEnums.VALID_TYPE) == null)
            return "add restaurant failed: invalid type format";
        else if (location < 1 || location > 1000)
            return "add restaurant failed: invalid location format";
        else {
            currentUser.addRestaurant(new Restaurant(name, type, location));
            return "restaurant added successfully";
        }
    }

    public static void showRestaurants(String command) throws ClassNotFoundException, SQLException {
        Pattern typePattern = Pattern.compile(RestaurantAdminMenuEnums.getString(RestaurantAdminMenuEnums.SHOW_RESTAURANT_OPTION));
        Matcher typeMatcher = typePattern.matcher(command);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String getManagerID = "SELECT * FROM tapsifood.accounts where name='" + currentUser.getUsername() + "'";
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
            String type = typeMatcher.group("type");
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

    public static String chargeAccount(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));

        if(amount < 1)
            return "charge account failed: invalid cost or price";

        currentUser.changeBalance(amount);
        return "charge account successful";
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
        }    }
}