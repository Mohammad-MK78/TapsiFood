package View;

import Controller.SnappFoodAdminMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SnappFoodAdminMenu {
    public static void run(Scanner scanner) {
        Matcher matcher;
        String command;

        while (true) {
            command = scanner.nextLine();

            if(SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.LOGOUT) != null){
                System.out.println("user logged out successfully");
                return;
            }

            else if((matcher = SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.ADD_RESTAURANT)) != null)
                System.out.println(SnappFoodAdminMenuController.addRestaurant(matcher));

            else if(SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.SHOW_RESTAURANT) != null)
                SnappFoodAdminMenuController.showRestaurant(command);

            else if((matcher = SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.REMOVE_RESTAURANT)) != null)
                System.out.printf(SnappFoodAdminMenuController.removeRestaurant(matcher));

            else if((matcher = SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.SET_DISCOUNT)) != null)
                System.out.println(SnappFoodAdminMenuController.addDiscount(matcher));

            else if(SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.SHOW_DISCOUNTS) != null)
                SnappFoodAdminMenuController.showDiscounts();

            else if(SnappFoodAdminMenuEnums.getMatcher(command, SnappFoodAdminMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("Snappfood admin menu");

            else
                System.out.println("invalid command!");
        }
    }
}
