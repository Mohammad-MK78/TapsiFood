package Controller;

import Model.Discount;
import Model.RestaurantManager;
import Model.SnappFood;
import View.SnappFoodAdminMenuEnums;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnappFoodAdminMenuController {

    public static void showRestaurant(String command) {
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
