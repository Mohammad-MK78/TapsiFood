package View;

import Controller.MainMenuController;
import Model.SnappFood;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    public static void run(Scanner scanner) throws IOException {
        Matcher matcher;
        String command;

        while(true) {
            command = scanner.nextLine();

            if(MainMenuEnums.getMatcher(command, MainMenuEnums.LOGOUT) != null){
                System.out.println("user logged out successfully");
                return;
            }

            else if((matcher = MainMenuEnums.getMatcher(command, MainMenuEnums.ENTER_MENU)) != null) {
                String result = MainMenuController.enterMenu(matcher);

                System.out.println(result);

                switch (result) {
                    case "enter menu successful: You are in the customer menu!":
                        CustomerMenu.run(scanner);
                        return;
                    case "enter menu successful: You are in the restaurant admin menu!":
                        RestaurantAdminMenu.run(scanner);
                        return;
                    case "enter menu successful: You are in the Snappfood admin menu!":
                        SnappFoodAdminMenu.run(scanner);
                        return;
                    case "enter menu successful: You are in the Delivery menu!":
                        DeliveryMenu.run(scanner);
                }

            }

            else if(MainMenuEnums.getMatcher(command, MainMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("main menu");

            else
                System.out.println("invalid command!");
        }
    }
}
