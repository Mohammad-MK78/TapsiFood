package controller;

import model.Discount;
import model.user.*;
import view.enums.commands.LoginMenuCommands;
import view.enums.commands.SnappfoodAdminMenuCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnappfoodAdminMenuController {
    public static void addRestaurant(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String type = matcher.group("type");
        if (!(Pattern.compile("[a-z]").matcher(username).find()
                || Pattern.compile("[A-Z]").matcher(username).find())
                || (LoginMenuCommands.getMatcher(username, LoginMenuCommands.VALID_USERNAME) == null))
            System.out.println("add restaurant failed: invalid username format");
        else if (App.getUserByUsername(username) != null)
            System.out.println("add restaurant failed: username already exists");
        else if (LoginMenuCommands.getMatcher(password, LoginMenuCommands.VALID_USERNAME) == null)
            System.out.println("add restaurant failed: invalid password format");
        else if (password.length() < 5
                || !Pattern.compile("[A-Z]").matcher(password).find()
                || !Pattern.compile("[a-z]").matcher(password).find()
                || !Pattern.compile("\\d").matcher(password).find())
            System.out.println("add restaurant failed: weak password");
        else if (SnappfoodAdminMenuCommands.getMatcher(type, SnappfoodAdminMenuCommands.VALID_TYPE) == null)
            System.out.println("add restaurant failed: invalid type format");
        else {
            Restaurant restaurant = new Restaurant(username, password, type);
            App.addRestaurant(restaurant);
            App.addUser(restaurant);
            System.out.println("add restaurant successful");
        }
    }

    public static void showRestaurant(Matcher matcher, String command) {
        int i = 1;
        if (!Pattern.compile("(\\s+-t\\s+(?<type>\\S+))").matcher(command).find()) {
            for (Restaurant restaurant : App.getRestaurants()) {
                System.out.println(i + ") " + restaurant.getUsername() + ": type=" + restaurant.getType() + " balance=" + restaurant.getBalance());
                i++;
            }
        } else {
            String type = matcher.group("type");
            for (Restaurant restaurant : App.getRestaurants()) {
                if (restaurant.getType().equals(type)) {
                    System.out.println(i + ") " + restaurant.getUsername() + ": type=" + restaurant.getType() + " balance=" + restaurant.getBalance());
                    i++;
                }
            }
        }
    }

    public static void removeRestaurant(Matcher matcher) {
        String username = matcher.group("username");
        for (Restaurant restaurant : App.getRestaurants()) {
            if (username.equals(restaurant.getUsername())) {
                App.removeRestaurant(restaurant);
                return;
            }
        }
        System.out.println("remove restaurant failed: restaurant not found");
    }

    public static void setDiscount(Matcher matcher) {
        String username = matcher.group("username");
        int amount = Integer.parseInt(matcher.group("amount"));
        String code = matcher.group("code");
        if (App.getCustomerByUsername(username) == null)
            System.out.println("set discount failed: username not found");
        else if (amount <= 0)
            System.out.println("set discount failed: invalid amount");
        else if (SnappfoodAdminMenuCommands.getMatcher(code, SnappfoodAdminMenuCommands.VALID_DISCOUNT_CODE) == null)
            System.out.println("set discount failed: invalid code format");
        else {
            Discount discount = new Discount(App.getCustomerByUsername(username), code, amount);
            App.addDiscount(discount);
            App.getCustomerByUsername(username).addDiscount(discount);
            System.out.println("set discount successful");
        }
    }

    public static void showDiscounts() {
        int i = 1;
        for (Discount discount : App.getDiscounts()) {
            System.out.println(i + ") " + discount.getCode() + " | amount=" + discount.getAmount() + " --> user=" + discount.getOwner().getUsername());
            i++;
        }
    }
}
