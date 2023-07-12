package com.example.Final.Controller;

import com.example.Final.Model.*;
import com.example.Final.View.RestaurantMenuEnums;

import java.sql.SQLException;
import java.util.regex.Matcher;

public class RestaurantMenuController {
    private static Restaurant currentRestaurant;

    public static void setCurrentRestaurant() { //TODO
        Restaurant restaurant = SnappFood.getCurrentRestaurant();
        currentRestaurant = new Restaurant(restaurant.getName(), restaurant.getType(), restaurant.getLocation(), restaurant.getRate());
    }

    public static Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public static int showBalance() {
        return currentRestaurant.getCredit();
    }

    public static String changeType(String type) {
        currentRestaurant.changeType(type);
        return "successful";
    }
    public static String showType() {
        return currentRestaurant.getType();
    }
    public static String addFood(String name, String category, int price, int cost) throws SQLException, ClassNotFoundException {

        if(!category.equalsIgnoreCase("Starter") && !category.equalsIgnoreCase("MainMeal") && !category.equalsIgnoreCase("Dessert"))
            return "invalid: category";

        else if(RestaurantMenuEnums.getMatcher(name, RestaurantMenuEnums.VALID_FOOD_NAME) == null)
            return "invalid: food name";

        else if(Restaurant.getFoodByName(name, currentRestaurant.getName()) != null)
            return "invalid: food already exists";

        else if(price < 1 || cost < 1)
            return "invalid: cost or price";

        currentRestaurant.addFood(new Food(currentRestaurant, name, category, price, cost));
        return "Successful";
    }
    public static String removeFood(String name) throws SQLException, ClassNotFoundException {
        Food food = Restaurant.getFoodByName(name, currentRestaurant.getName());
        if(food == null)
            return "failed: food not found";

        Restaurant.removeFood(food);
        return "Successful";
    }

    public static String reply(Matcher matcher) {
        int num = Integer.parseInt(matcher.group("commentNum"));
        String message = matcher.group("comment");
        if (num > currentRestaurant.getComments().size() || currentRestaurant.getComments().get(num) == null)
            return "comment not found!";
        currentRestaurant.reply(num, message);
        return "replied successfully!";
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