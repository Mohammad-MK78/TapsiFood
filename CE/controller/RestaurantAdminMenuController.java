package controller;

import model.Food;
import model.user.App;
import model.user.Restaurant;
import view.enums.commands.RestaurantAdminMenuCommands;

import java.util.regex.Matcher;

public class RestaurantAdminMenuController {
    public static void chargeAccount(Matcher matcher) {
            int amount = Integer.parseInt(matcher.group("amount"));
            if (amount <= 0)
                System.out.println("charge account failed: invalid cost or price");
            else {
                App.getCurrentUser().addBalance(amount);
                System.out.println("charge account successful");
            }
    }

    public static void addFood(Matcher matcher) {
        String name = matcher.group("name");
        String category = matcher.group("category");
        int price = Integer.parseInt(matcher.group("price"));
        int cost = Integer.parseInt(matcher.group("cost"));
        Food food = new Food(name, category, (Restaurant) App.getCurrentUser(), price, cost);
        if (!category.equals("starter")
                && !category.equals("entree")
                && !category.equals("dessert"))
            System.out.println("add food failed: invalid category");
        else if (RestaurantAdminMenuCommands.getMatcher(name, RestaurantAdminMenuCommands.VALID_NAME) == null)
            System.out.println("add food failed: invalid food name");
        else if (((Restaurant) App.getCurrentUser()).IsFoodExist(name))
            System.out.println("add food failed: food already exists");
        else if ((cost <= 0) || (price <= 0))
            System.out.println("add food failed: invalid cost or price");
        else {
            ((Restaurant) App.getCurrentUser()).addFood(food);
            System.out.println("add food successful");
        }
    }

    public static void removeFood(Matcher matcher) {
        String name = matcher.group("name");
        for (Food food : ((Restaurant) App.getCurrentUser()).getFoods()) {
            if (food.getName().equals(name)) {
                ((Restaurant) App.getCurrentUser()).removeFood(food);
                return;
            }
        }
        System.out.println("remove food failed: food not found");
    }
}
