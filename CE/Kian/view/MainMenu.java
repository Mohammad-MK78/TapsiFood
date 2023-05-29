package view;

import controller.MainMenuController;
import view.enums.commands.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    public void run(Scanner scanner) {
        String command;
        String result;
        while (true) {
            command = scanner.nextLine();
            if (MainMenuCommands.getMatcher(command, MainMenuCommands.LOGOUT) != null)
                return;
            else if (MainMenuCommands.getMatcher(command, MainMenuCommands.SHOW_MENU) != null)
                System.out.println("main menu");
            else if (MainMenuCommands.getMatcher(command, MainMenuCommands.ENTER_CUSTOMER_MENU) != null) {
                result = MainMenuController.enterCustomerMenu();
                System.out.println(result);
                if (result.equals("enter menu successful: You are in the customer menu!")) {
                    new CustomerMenu().run(scanner);
                    return;
                }
            } else if(MainMenuCommands.getMatcher(command, MainMenuCommands.ENTER_RESTAURANT_ADMIN_MENU) != null) {
                result = MainMenuController.enterRestaurantAdminMenu();
                System.out.println(result);
                if (result.equals("enter menu successful: You are in the restaurant admin menu!")) {
                    new RestaurantAdminMenu().run(scanner);
                    return;
                }
            } else if(MainMenuCommands.getMatcher(command, MainMenuCommands.ENTER_SNAPPFOOD_ADMIN_MENU) != null){
                result = MainMenuController.enterSnappfoodAdminMenu();
                System.out.println(result);
                if (result.equals("enter menu successful: You are in the Snappfood admin menu!")) {
                    new SnappfoodAdminMenu().run(scanner);
                    return;
                }
            } else if(Pattern.compile("^(\\s*enter)").matcher(command).find())
                System.out.println("enter menu failed: invalid menu name");
            else
                System.out.println("invalid command!");
        }
    }
}
