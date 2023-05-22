package view;

import controller.CustomerMenuController;
import controller.RestaurantAdminMenuController;
import model.user.App;
import view.enums.commands.CustomerMenuCommands;
import view.enums.commands.RestaurantAdminMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CustomerMenu {
    public void run(Scanner scanner) {
        String command;
        Matcher matcher;
        while (true) {
            command = scanner.nextLine();
            if (RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.LOGOUT) != null)
                return;
            else if (RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.SHOW_MENU) != null)
                System.out.println("customer menu");
            else if ((matcher = RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.CHARGE_ACCOUNT)) != null)
                RestaurantAdminMenuController.chargeAccount(matcher);
            else if (RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.SHOW_BALANCE) != null)
                System.out.println(App.getCurrentUser().getBalance());
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.SHOW_RESTAURANT)) != null)
                CustomerMenuController.showRestaurant(matcher, command);
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.SHOW_MENU)) != null)
                CustomerMenuController.showMenu(matcher, command);
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.ADD_TO_CART)) != null)
                CustomerMenuController.addToCart(matcher, command);
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.REMOVE_FROM_CART)) != null)
                CustomerMenuController.removeFromCart(matcher, command);
            else if (CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.SHOW_CART) != null)
                CustomerMenuController.showCart();
            else if (CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.SHOW_DISCOUNTS) != null)
                CustomerMenuController.showDiscounts();
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.PURCHASE_CART)) != null)
                CustomerMenuController.purchaseCart(matcher, command);
            else
                System.out.println("invalid command!");
        }
    }
}
