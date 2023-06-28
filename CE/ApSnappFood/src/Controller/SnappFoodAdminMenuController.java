package Controller;

import Model.Discount;
import Model.RestaurantManager;
import Model.SnappFood;
import View.SnappFoodAdminMenuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnappFoodAdminMenuController {

    public static String addRestaurant(Matcher matcher) {
        String name = matcher.group("name");
        String password = matcher.group("password");
        String type = matcher.group("type");

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

        else {
            SnappFood.addRestaurantManager(new RestaurantManager(name, password, type));
            return "restaurant added successfully";
        }
    }

    public static void showRestaurant(String command) {
        Pattern typePattern = Pattern.compile(SnappFoodAdminMenuEnums.getString(SnappFoodAdminMenuEnums.SHOW_RESTAURANT_OPTION));
        Matcher typeMatcher = typePattern.matcher(command);
        int index = 1;

        if(typeMatcher.find()) {
            String type = typeMatcher.group("type");
            for(RestaurantManager restaurant : SnappFood.getRestaurantManagers())
                if(restaurant.getType().equals(type)) {
                    System.out.println(index + ") " + restaurant);
                    index++;
                }
        }
        else {
            for(RestaurantManager restaurant : SnappFood.getRestaurantManagers()) {
                System.out.println(index + ") " + restaurant);
                index++;
            }
        }
    }

    public static String removeRestaurant(Matcher matcher) {
        String restaurantName = matcher.group("name");

        if(SnappFood.getRestaurantManagerByUsername(restaurantName) != null) {
            SnappFood.removeUser(SnappFood.getRestaurantManagerByUsername(restaurantName));
            return "";
        }

        return "remove restaurant failed: restaurant not found\n";
    }

    public static String addDiscount(Matcher matcher) {
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
