package Controller;

import Model.*;
import View.SnappFoodAdminMenuEnums;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestaurantAdminMenuController {
    private static RestaurantManager currentUser;

    public static void setCurrentUser() {
        User user = SnappFood.getCurrentUser();
        currentUser = new RestaurantManager(user.getUsername(), user.getPassword(), user.getLocation(), user.getSecurity_question());
    }
    public static String addRestaurant(Matcher matcher) throws SQLException, ClassNotFoundException {
        String name = matcher.group("name");
        String password = matcher.group("password");
        String type = matcher.group("type");
        int location = Integer.parseInt(matcher.group("location"));

        if(SnappFoodAdminMenuEnums.getMatcher(name, SnappFoodAdminMenuEnums.VALID_USERNAME) == null)
            return "add restaurant failed: invalid username format";

        else if(SnappFood.getUserByUsername(name) != null)
            return "add restaurant failed: username already exists";

        else if(SnappFoodAdminMenuEnums.getMatcher(password, SnappFoodAdminMenuEnums.VALID_PASSWORD) == null)
            return "add restaurant failed: invalid password format";

        else if(password.length() < 5 ||
                !Pattern.compile("[a-z]").matcher(password).find() ||
                !Pattern.compile("[A-Z]").matcher(password).find() ||
                !Pattern.compile("\\d").matcher(password).find())
            return "add restaurant failed: weak password";

        else if(SnappFoodAdminMenuEnums.getMatcher(type, SnappFoodAdminMenuEnums.VALID_TYPE) == null)
            return "add restaurant failed: invalid type format";
        else if (location < 1 || location > 1000)
            return "add restaurant failed: invalid location format";
        else {
            SnappFood.addRestaurantManager(new RestaurantManager(name, password, location, "security_question"));
            return "restaurant added successfully";
        }
    }

    public static void showRestaurants(String command) {
        Pattern typePattern = Pattern.compile(SnappFoodAdminMenuEnums.getString(SnappFoodAdminMenuEnums.SHOW_RESTAURANT_OPTION));
        Matcher typeMatcher = typePattern.matcher(command);
        int index = 1;
        if(typeMatcher.find()) {
            String type = typeMatcher.group("type");
            for(RestaurantManager restaurant : SnappFood.getRestaurantManagers())
                if(restaurant.getType().equals(type)) {
                    System.out.println(index + ") " + restaurant + " -> loc :" + restaurant.getLocation());
                    index++;
                }
        }
        else {
            for(RestaurantManager restaurant : SnappFood.getRestaurantManagers()) {
                System.out.println(index + ") " + restaurant + " -> loc :" + restaurant.getLocation());
                index++;
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