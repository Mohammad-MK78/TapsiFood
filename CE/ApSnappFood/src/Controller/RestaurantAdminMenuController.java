package Controller;

import Model.*;
import View.RestaurantAdminMenuEnums;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class RestaurantAdminMenuController {
    private static RestaurantManager currentUser;

    public static void setCurrentUser() {
        User user = SnappFood.getCurrentUser();
        currentUser = new RestaurantManager(user.getUsername(), user.getPassword(),"type", user.getLocation(), user.getSecurity_question());
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

    public static String changeType(Matcher matcher) {
        String type = matcher.group("type");
        currentUser.changeType(type);
        return "type changed successfully";
    }
    public static String addFood(Matcher matcher) {
        String name = matcher.group("name");
        String category = matcher.group("category");
        int price = Integer.parseInt(matcher.group("price"));
        int cost = Integer.parseInt(matcher.group("cost"));

        if(!category.equals("Starter") && !category.equals("MainMeal") && !category.equals("Dessert"))
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

    public static String reply(Matcher matcher) {
        int num = Integer.parseInt(matcher.group("commentNum"));
        String message = matcher.group("comment");
        if (num > currentUser.getComments().size() || currentUser.getComments().get(num) == null)
            return "comment not found!";
        currentUser.reply(num, message);
        return "reply added!";
    }
    public static String removeFood(Matcher matcher) {
        String name = matcher.group("name");

        if(currentUser.getFoodByName(name) == null)
            return "remove food failed: food not found\n";

        currentUser.removeFood(currentUser.getFoodByName(name));
        return "";
    }
    public static ArrayList showComment() {
        return currentUser.getComments();
    }
}