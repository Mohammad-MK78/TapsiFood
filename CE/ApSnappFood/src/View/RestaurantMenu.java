package View;

import Controller.RestaurantMenuController;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class RestaurantMenu {
    public static void run(Scanner scanner) throws SQLException, ClassNotFoundException {
        Matcher matcher;
        String command;

        RestaurantMenuController.setCurrentRestaurant();

        while(true) {
            command = scanner.nextLine();

            if(RestaurantMenuEnums.getMatcher(command, RestaurantMenuEnums.LOGOUT) != null){
                System.out.println("user logged out successfully");
                return;
            }

            else if(RestaurantMenuEnums.getMatcher(command, RestaurantMenuEnums.SHOW_BALANCE) != null)
                System.out.println(RestaurantMenuController.showBalance());

            else if((matcher = RestaurantMenuEnums.getMatcher(command, RestaurantMenuEnums.ADD_FOOD)) != null)
                System.out.println(RestaurantMenuController.addFood(matcher));

            else if (RestaurantMenuEnums.getMatcher(command, RestaurantMenuEnums.SHOW_COMMENTS) != null)
                System.out.println(RestaurantMenuController.showComment());

            else if ((matcher = RestaurantMenuEnums.getMatcher(command, RestaurantMenuEnums.REPLY_TO_COMMENT)) != null)
                System.out.println(RestaurantMenuController.reply(matcher));

            else if ((matcher = RestaurantMenuEnums.getMatcher(command, RestaurantMenuEnums.CHANGE_TYPE)) != null)
                System.out.println(RestaurantMenuController.changeType(matcher));

            else if((matcher = RestaurantMenuEnums.getMatcher(command, RestaurantMenuEnums.REMOVE_FOOD)) != null)
                System.out.printf(RestaurantMenuController.removeFood(matcher));

            else if(RestaurantMenuEnums.getMatcher(command, RestaurantMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("restaurant admin menu");

            else
                System.out.println("invalid command!");
        }
    }
}
