package View;

import Controller.RestaurantAdminMenuController;
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
            else if((matcher = RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.ADD_RESTAURANT)) != null)
                System.out.println(RestaurantAdminMenuController.addRestaurant(matcher));

            else if(RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.SHOW_RESTAURANTS) != null)
                RestaurantAdminMenuController.showRestaurants(command);

            else if((matcher = RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.ENTER_RESTAURANT)) != null) {
                String result = RestaurantAdminMenuController.enterRestaurant(matcher);
                System.out.println(result);
                if(result.equals("enter successful"))
                    RestaurantMenu.run(scanner);
            }

            else if((matcher = RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.REMOVE_RESTAURANT)) != null)
                System.out.println(RestaurantAdminMenuController.removeRestaurant(matcher));

            else if((matcher = RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.CHARGE_ACCOUNT)) != null)
                System.out.println(RestaurantAdminMenuController.chargeAccount(matcher));

            else if(RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.SHOW_BALANCE) != null)
                System.out.println(RestaurantAdminMenuController.showBalance());

            else if(RestaurantAdminMenuEnums.getMatcher(command, RestaurantAdminMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("restaurant admin menu");

            else
                System.out.println("invalid command!");
        }
    }
}
