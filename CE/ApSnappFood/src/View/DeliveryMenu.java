package View;

import Controller.CustomerMenuController;
import Controller.DeliveryMenuController;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class DeliveryMenu {
    public static void run(Scanner scanner) throws IOException {
        Matcher matcher;
        String command;

        DeliveryMenuController.setCurrentUser();

        while (true) {
            command = scanner.nextLine();

            if (DeliveryMenuEnums.getMatcher(command, DeliveryMenuEnums.LOGOUT) != null){
                System.out.println("user logged out successfully");
                return;
            }
            else if(DeliveryMenuEnums.getMatcher(command, DeliveryMenuEnums.SHOW_RESTAURANT) != null)
                DeliveryMenuController.showRestaurant();
            else if(DeliveryMenuEnums.getMatcher(command, DeliveryMenuEnums.SHOW_DESTINATION) != null)
                DeliveryMenuController.showDestination();
            else if (DeliveryMenuEnums.getMatcher(command, DeliveryMenuEnums.SHOW_LOCATION) != null)
                DeliveryMenuController.showLocation();
            else if (DeliveryMenuEnums.getMatcher(command, DeliveryMenuEnums.SHOW_PATH) != null)
                DeliveryMenuController.show_path();
            else if (DeliveryMenuEnums.getMatcher(command, DeliveryMenuEnums.SHOW_DISTANCE) != null)
                DeliveryMenuController.show_distance();
            else if(DeliveryMenuEnums.getMatcher(command, DeliveryMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("delivery menu");

        }
    }
}