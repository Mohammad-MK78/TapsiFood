package com.example.Final.View;

import com.example.Final.Controller.RestaurantAdminMenuController;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class RestaurantAdminMenu {
    public static void run(Scanner scanner) throws SQLException, ClassNotFoundException {
        Matcher matcher;
        String command;

        RestaurantAdminMenuController.setCurrentUser();

        while(true) {
            command = scanner.nextLine();

            if(RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.LOGOUT) != null){
                System.out.println("user logged out successfully");
                return;
            }

            else if(RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.SHOW_RESTAURANTS) != null)
                RestaurantAdminMenuController.showRestaurants(command);



            else if(RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.SHOW_BALANCE) != null)
                System.out.println(RestaurantAdminMenuController.showBalance());

            else if(RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("restaurant admin menu");

            else
                System.out.println("invalid command!");
        }
    }
}
