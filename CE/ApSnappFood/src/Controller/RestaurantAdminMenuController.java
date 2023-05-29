package Controller;

import Model.Food;
import Model.RestaurantManager;
import Model.SnappFood;
import View.RestaurantAdminMenuEnums;

import java.util.regex.Matcher;

public class RestaurantAdminMenuController {
    private static RestaurantManager currentUser;

    public static void setCurrentUser() {
        currentUser = (RestaurantManager) SnappFood.getCurrentUser();
    }

    public static String chargeAccount(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));

        if(amount < 1)
            return "charge account failed: invalid cost or price";

        currentUser.changeBalance(amount);
        return "charge account successful";
    }

    public static int showBalance() {
        return currentUser.getBalance();
    }

    public static String addFood(Matcher matcher) {
        String name = matcher.group("name");
        String category = matcher.group("category");
        int price = Integer.parseInt(matcher.group("price"));
        int cost = Integer.parseInt(matcher.group("cost"));

        if(!category.equals("starter") && !category.equals("entree") && !category.equals("dessert"))
            return "add food failed: invalid category";

        else if(RestaurantAdminMenuEnums.getMatcher(name, RestaurantAdminMenuEnums.VALID_FOOD_NAME) == null)
            return "add food failed: invalid food name";

        else if(currentUser.getFoodByName(name) != null)
            return "add food failed: food already exists";

        else if(price < 1 || cost < 1)
            return "add food failed: invalid cost or price";

        currentUser.addFood(new Food(currentUser, name, category, price, cost));
        return "add food successful";
    }

    public static String removeFood(Matcher matcher) {
        String name = matcher.group("name");

        if(currentUser.getFoodByName(name) == null)
            return "remove food failed: food not found\n";

        currentUser.removeFood(currentUser.getFoodByName(name));
        return "";
    }
}
