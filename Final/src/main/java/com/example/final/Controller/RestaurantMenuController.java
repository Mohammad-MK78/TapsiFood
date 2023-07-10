package Controller;

import Model.*;
import View.RestaurantMenuEnums;

import java.sql.SQLException;
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

        else if(Restaurant.getFoodByName(name, currentRestaurant.getName()) != null)
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
        return "replied successfully!";
    }
    public static String removeFood(Matcher matcher) throws SQLException, ClassNotFoundException {
        String name = matcher.group("name");

        if(Restaurant.getFoodByName(name, currentRestaurant.getName()) == null)
            return "remove food failed: food not found\n";

        Restaurant.getFoodByName(name, currentRestaurant.getName());
        return "";
    }
    public static String showComment() {
        return currentRestaurant.getComments().toString();
    }
    public static void showOrderHistory() {
        int index = 1;
        for (Cart cart : currentRestaurant.getHistory()) {
            System.out.println(index + ")");
            cart.showCart();
            index++;
        }
    }
    public static void showOngoingOrders() {
        int index = 1;
        for (Cart cart : currentRestaurant.getOngoingOrders()) {
            System.out.println(index + ")");
            cart.showCart();
            index++;
        }
    }
}