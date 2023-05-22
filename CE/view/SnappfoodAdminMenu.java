package view;

import controller.SnappfoodAdminMenuController;
import view.enums.commands.MainMenuCommands;
import view.enums.commands.SnappfoodAdminMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SnappfoodAdminMenu {
    public void run(Scanner scanner) {
        String command;
        Matcher matcher;
        while (true) {
            command = scanner.nextLine();
            if (SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.LOGOUT) != null)
                return;
            else if (MainMenuCommands.getMatcher(command, MainMenuCommands.SHOW_MENU) != null)
                System.out.println("Snappfood admin menu");
            else if ((matcher = SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.ADD_RESTAURANT)) != null)
                SnappfoodAdminMenuController.addRestaurant(matcher);
            else if ((matcher = SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.SHOW_RESTAURANT)) != null)
                SnappfoodAdminMenuController.showRestaurant(matcher, command);
            else if ((matcher = SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.REMOVE_RESTAURANT)) != null)
                SnappfoodAdminMenuController.removeRestaurant(matcher);
            else if((matcher = SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.SET_DISCOUNT)) != null)
                SnappfoodAdminMenuController.setDiscount(matcher);
            else if(SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.SHOW_DISCOUNTS) != null)
                SnappfoodAdminMenuController.showDiscounts();
            else
                System.out.println("invalid command!");
        }
    }
}
