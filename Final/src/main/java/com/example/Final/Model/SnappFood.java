package com.example.Final.Model;

import com.example.Final.Controller.RestaurantAdminMenuController;

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

    public static void addDiscount(Discount discount) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();

        String getCustomerID = "SELECT * FROM tapsifood.accounts where username='" + discount.getDiscountUser().getUsername() + "'";
        int customerID = 0;
        ResultSet getCID = statement.executeQuery(getCustomerID);
        if (getCID.next())
            customerID = getCID.getInt("id");

        String sql = "INSERT INTO tapsifood.discount(customerID, amount, code) VALUES ('"+customerID+"', '"+discount.getDiscountAmount()+"', '"+discount.getCode()+"')";
        statement.executeUpdate(sql);

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

    public static void updateDeliveries() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();

        String sqlCheckDelivery = "SELECT * FROM tapsifood.accounts where position='delivery'";
        ResultSet deliveryCheck = statement.executeQuery(sqlCheckDelivery);

        ArrayList<Delivery> deliveries = new ArrayList<>();

        while (deliveryCheck.next()) {
            String username = deliveryCheck.getString("username");
            String password = deliveryCheck.getString("password");
            int location = deliveryCheck.getInt("location");
            String securityQuestion = deliveryCheck.getString("security_question");
            int credit = deliveryCheck.getInt("credit");
            int is_busy = deliveryCheck.getInt("is_busy");
            deliveries.add(new Delivery(username, password, location, securityQuestion, credit, is_busy));
        }
        SnappFood.deliveries = deliveries;
    }

    public static ArrayList<Delivery> getDeliveries() throws SQLException, ClassNotFoundException {
        updateDeliveries();
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
    public static void removeRestaurant(Restaurant restaurant) throws ClassNotFoundException, SQLException {
        String name = restaurant.getName();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlRegAdmin = "SELECT * FROM tapsifood.restaurants where name='" + name + "'";
        ResultSet usernameCheck = statement.executeQuery(sqlRegAdmin);
        if (usernameCheck.next()) {
            String remove = "DELETE FROM tapsifood.restaurants where name='" + name + "'";
            statement.executeUpdate(remove);
        }
        RestaurantAdminMenuController.getCurrentUser().getRestaurants().remove(restaurant);
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

            return new Restaurant(name, type, location, 0);
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

    public static void removeDiscount(Discount discount) throws ClassNotFoundException, SQLException {
        if (discount != null){
            String username = discount.getUsername();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();

            String getCustomerID = "SELECT * FROM tapsifood.accounts where username='" + username + "'";
            int customerID = 0;
            ResultSet getCID = statement.executeQuery(getCustomerID);
            if (getCID.next())
                customerID = getCID.getInt("id");

            String sqlDiscountCheck = "SELECT * FROM tapsifood.discount where customerID='" + customerID + "' AND code='" + discount.getCode() + "'";
            ResultSet discountCheck = statement.executeQuery(sqlDiscountCheck);

            if (discountCheck.next()) {
                discounts.remove(discount);
                String remove = "DELETE FROM tapsifood.discount WHERE customerID='" + customerID + "' AND code='" + discount.getCode() + "'";
                statement.executeUpdate(remove);
            }
        }
    }
}
