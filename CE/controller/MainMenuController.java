package controller;

import model.user.App;
import model.user.Customer;
import model.user.Restaurant;

public class MainMenuController {
    public static String enterCustomerMenu() {
        if (App.getCurrentUser() instanceof Customer)
            return "enter menu successful: You are in the customer menu!";
        else
            return "enter menu failed: access denied";
    }

    public static String enterRestaurantAdminMenu() {
        if (App.getCurrentUser() instanceof Restaurant)
            return "enter menu successful: You are in the restaurant admin menu!";
        else
            return "enter menu failed: access denied";
    }

    public static String enterSnappfoodAdminMenu() {
        if (App.getCurrentUser().equals(App.getSnappfoodAdmin()))
            return "enter menu successful: You are in the Snappfood admin menu!";
        else
            return "enter menu failed: access denied";
    }
}
