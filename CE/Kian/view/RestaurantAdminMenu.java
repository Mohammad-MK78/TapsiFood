package view;

import controller.RestaurantAdminMenuController;
import model.user.App;
import view.enums.commands.RestaurantAdminMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RestaurantAdminMenu {
    public void run(Scanner scanner) {
        String command;
        Matcher matcher;
        while (true) {
            command = scanner.nextLine();
            if (RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.LOGOUT) != null)
                return;
            else if (RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.SHOW_MENU) != null)
                System.out.println("restaurant admin menu");
            else if ((matcher = RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.CHARGE_ACCOUNT)) != null)
                RestaurantAdminMenuController.chargeAccount(matcher);
            else if (RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.SHOW_BALANCE) != null)
                System.out.println(App.getCurrentUser().getBalance());
            else if ((matcher = RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.ADD_FOOD)) != null)
                RestaurantAdminMenuController.addFood(matcher);
            else if ((matcher = RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.REMOVE_FOOD)) != null)
                RestaurantAdminMenuController.removeFood(matcher);
            else
                System.out.println("invalid command!");
        }
    }
}
