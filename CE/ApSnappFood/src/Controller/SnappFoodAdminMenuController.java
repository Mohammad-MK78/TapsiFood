package Controller;

import Model.Discount;
import Model.Restaurant;
import Model.SnappFood;
import View.SnappFoodAdminMenuEnums;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnappFoodAdminMenuController {

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
                ResultSet managerCheck = statement.executeQuery(sqlManagerType);
                String managerName = managerCheck.getString("username");
                String name = typeCheck.getString("name");
                Restaurant restaurant = SnappFood.getRestaurantByName(name);
                System.out.println((index++) + ". " + restaurant.getName() + " | Manager: " + managerName + "-> type: " + restaurant.getType() + " | rating: " + restaurant.getRating() +  " | loc: " + restaurant.getLocation());
            }
        }
        else {
            String type = typeMatcher.group("type");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sqlCheckRestaurant = "SELECT * FROM tapsifood.restaurants";
            ResultSet restaurantCheck = statement.executeQuery(sqlCheckRestaurant);
            int index = 1, managerID;
            while (restaurantCheck.next()) {
                managerID = restaurantCheck.getInt("managerID");
                String sqlManagerType = "SELECT * FROM tapsifood.accounts where id='" + managerID + "'";
                ResultSet managerCheck = statement.executeQuery(sqlManagerType);
                String managerName = managerCheck.getString("username");
                String name = restaurantCheck.getString("name");
                Restaurant restaurant = SnappFood.getRestaurantByName(name);
                System.out.println((index++) + ". " + restaurant.getName() + " | Manager: " + managerName + "-> type: " + restaurant.getType() + " | rating: " + restaurant.getRating() +  " | loc: " + restaurant.getLocation());
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
