package com.example.Final.View;

import com.example.Final.Controller.SnappFoodAdminMenuController;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SnappFoodAdminMenu {
    public static void run(Scanner scanner) throws SQLException, ClassNotFoundException {
        Matcher matcher;
        String command;

        while (true) {
            command = scanner.nextLine();

            if(SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.LOGOUT) != null){
                System.out.println("user logged out successfully");
                return;
            }

            else if((matcher = SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.REMOVE_RESTAURANT_MANAGER)) != null)
                System.out.printf(SnappFoodAdminMenuController.removeRestaurantManager(matcher));


            else if(SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.SHOW_RESTAURANT) != null)
                SnappFoodAdminMenuController.showRestaurant(command);


            else if(SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.SHOW_DISCOUNTS) != null)
                SnappFoodAdminMenuController.showDiscounts();

            else if(SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("Snappfood admin menu");

            else
                System.out.println("invalid command!");
        }
    }
}
