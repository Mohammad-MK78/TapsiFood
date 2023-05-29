package View;

import Controller.RestaurantAdminMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RestaurantAdminMenu {
    public static void run(Scanner scanner) {
        Matcher matcher;
        String command;

        RestaurantAdminMenuController.setCurrentUser();

        while(true) {
            command = scanner.nextLine();

            if(RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.LOGOUT) != null) return;

            else if((matcher = RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.CHARGE_ACCOUNT)) != null)
                System.out.println(RestaurantAdminMenuController.chargeAccount(matcher));

            else if(RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.SHOW_BALANCE) != null)
                System.out.println(RestaurantAdminMenuController.showBalance());

            else if((matcher = RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.ADD_FOOD)) != null)
                System.out.println(RestaurantAdminMenuController.addFood(matcher));

            else if((matcher = RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.REMOVE_FOOD)) != null)
                System.out.printf(RestaurantAdminMenuController.removeFood(matcher));

            else if(RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("restaurant admin menu");

            else
                System.out.println("invalid command!");
        }
    }
}
