package Controller;

import Model.*;
import View.RestaurantMenuEnums;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class RestaurantMenuController {
    private static Restaurant currentRestaurant;

    public static void setCurrentRestaurant() { //TODO
        Restaurant restaurant = SnappFood.getCurrentRestaurant();
        currentRestaurant = new Restaurant(restaurant.getName(), restaurant.getType(), restaurant.getLocation());
    }

    public static int showBalance() {
        return currentRestaurant.getCredit();
    }

    public static String changeType(Matcher matcher) {
        String type = matcher.group("type");
        currentRestaurant.changeType(type);
        return "type changed successfully";
    }
    public static String addFood(Matcher matcher) throws SQLException, ClassNotFoundException {
        String name = matcher.group("name");
        String category = matcher.group("category");
        int price = Integer.parseInt(matcher.group("price"));
        int cost = Integer.parseInt(matcher.group("cost"));

        if(!category.equals("Starter") && !category.equals("MainMeal") && !category.equals("Dessert"))
            return "add food failed: invalid category";

        else if(RestaurantMenuEnums.getMatcher(name, RestaurantMenuEnums.VALID_FOOD_NAME) == null)
            return "add food failed: invalid food name";

        else if(currentRestaurant.getFoodByName(name) != null)
            return "add food failed: food already exists";

        else if(price < 1 || cost < 1)
            return "add food failed: invalid cost or price";

        currentRestaurant.addFood(new Food(currentRestaurant, name, category, price, cost));
        return "add food successful";
    }

    public static String reply(Matcher matcher) {
        int num = Integer.parseInt(matcher.group("commentNum"));
        String message = matcher.group("comment");
        if (num > currentRestaurant.getComments().size() || currentRestaurant.getComments().get(num) == null)
            return "comment not found!";
        currentRestaurant.reply(num, message);
        return "reply added!";
    }
    public static String removeFood(Matcher matcher) throws SQLException, ClassNotFoundException {
        String name = matcher.group("name");

        if(currentRestaurant.getFoodByName(name) == null)
            return "remove food failed: food not found\n";

        currentRestaurant.removeFood(currentRestaurant.getFoodByName(name));
        return "";
    }
    public static ArrayList showComment() {
        return currentRestaurant.getComments();
    }
}