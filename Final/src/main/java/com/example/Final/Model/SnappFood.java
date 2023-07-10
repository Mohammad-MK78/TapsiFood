package com.example.Final.Model;

import java.sql.*;
import java.util.ArrayList;

public class SnappFood {
    private static User currentUser;
    private static Restaurant currentRestaurant;
    private static ArrayList<User> users = new ArrayList<>();
    private static SnappFoodManager snappFoodManager;
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<RestaurantManager> restaurantManagers = new ArrayList<>();
    private static ArrayList<Delivery> deliveries = new ArrayList<>();
    private static ArrayList<Discount> discounts = new ArrayList<>();

    public static void addDiscount(Discount discount) {
        discounts.add(discount);
        discount.getDiscountUser().addDiscount(discount);
    }
    public static ArrayList<Discount> getDiscounts() {
        return discounts;
    }
    public static Discount getDiscountByCode(String code) {
        for(Discount discount : discounts)
            if(discount.getCode().equals(code))
                return discount;
        return null;
    }
    public static ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }
    public static ArrayList<RestaurantManager> getRestaurantManagers() {
        return restaurantManagers;
    }
    public static void removeUser(User user) throws ClassNotFoundException, SQLException {
        String username = user.getUsername();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlRegAdmin = "SELECT * FROM tapsifood.accounts where username='" + username + "'";
        ResultSet usernameCheck = statement.executeQuery(sqlRegAdmin);
        if (usernameCheck.next()) {
            String remove = "DELETE FROM tapsifood.accounts WHERE username='" + username + "'";
            statement.executeUpdate(remove);
        }

        users.remove(user);
        if(user instanceof Customer)
            customers.remove(user);
        else
            restaurantManagers.remove(user);
    }

    public static void setSnappFoodManager(SnappFoodManager snappFoodManager) {
        SnappFood.snappFoodManager = snappFoodManager;
        users.add(snappFoodManager);
    }

    public static void addCustomer(Customer customer) {
        customers.add(customer);
        users.add(customer);
    }

    public static void addRestaurantManager(RestaurantManager restaurantManager) {
        restaurantManagers.add(restaurantManager);
        users.add(restaurantManager);
    }
    public static void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
        users.add(delivery);
    }

    public static User getUserByUsername(String username) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlRegAdmin = "SELECT * FROM tapsifood.accounts where username='" + username + "'";
        ResultSet usernameCheck = statement.executeQuery(sqlRegAdmin);
        if (usernameCheck.next()) {
            String password = usernameCheck.getString("password");
            String position = usernameCheck.getString("position");
            String securityQuestion = usernameCheck.getString("security_question");
            int location = usernameCheck.getInt("location");
            int credit = usernameCheck.getInt("credit");
            int debt = usernameCheck.getInt("debt");
            int is_busy = usernameCheck.getInt("is_busy");
            return new User(username, password, location,position, securityQuestion, credit, debt, is_busy);
        }
        return null;
    }

    public static Restaurant getRestaurantByName(String name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlCheckRestaurant = "SELECT * FROM tapsifood.restaurants where name='" + name + "'";
        ResultSet nameCheck = statement.executeQuery(sqlCheckRestaurant);
        if (nameCheck.next()) {
            String type = nameCheck.getString("type");
            int location = nameCheck.getInt("location");

            return new Restaurant(name, type, location);
        }
        return null;
    }
    public static Customer getCustomerByUsername(String username) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlCheckCustomer = "SELECT * FROM tapsifood.accounts where username='" + username + "' AND position='customer'";
        ResultSet usernameCheck = statement.executeQuery(sqlCheckCustomer);
        if (usernameCheck.next()) {
            String password = usernameCheck.getString("password");
            String security_question = usernameCheck.getString("security_question");
            int location = usernameCheck.getInt("location");
            int credit = usernameCheck.getInt("credit");
            int debt = usernameCheck.getInt("debt");
            return new Customer(username, password, location, security_question, credit, debt);
        }
        return null;
    }

    public static RestaurantManager getRestaurantManagerByUsername(String username) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlCheckCustomer = "SELECT * FROM tapsifood.accounts where username='" + username + "' AND position='manager'";
        ResultSet usernameCheck = statement.executeQuery(sqlCheckCustomer);
        if (usernameCheck.next()) {
            String password = usernameCheck.getString("password");
            String security_question = usernameCheck.getString("security_question");
            int location = usernameCheck.getInt("location");
            int credit = usernameCheck.getInt("credit");
            return new RestaurantManager(username, password, security_question, credit);
        }
        return null;
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SnappFood.currentUser = currentUser;
    }

    public static Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public static void setCurrentRestaurant(Restaurant currentRestaurant) {
        SnappFood.currentRestaurant = currentRestaurant;
    }

    public static void removeDiscount(Discount discount) {
        if(discount != null) {
            discounts.remove(discount);
            ((Customer) currentUser).removeDiscount(discount);
        }
    }
}
