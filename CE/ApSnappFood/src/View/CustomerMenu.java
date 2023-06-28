package View;

import Controller.CustomerMenuController;
import Model.Customer;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CustomerMenu {

    public static void run(Scanner scanner) {
        Matcher matcher;
        String command;

        CustomerMenuController.setCurrentUser();

        while(true) {
            command = scanner.nextLine();

            if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.LOGOUT) != null) return;

            else if((matcher = CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.CHARGE_ACCOUNT)) != null)
                System.out.println(CustomerMenuController.chargeAccount(matcher));

            else if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SHOW_BALANCE) != null)
                System.out.println(CustomerMenuController.showBalance());

            else if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SHOW_RESTAURANT) != null)
                CustomerMenuController.showRestaurant(command);

            else if((matcher = CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SHOW_MENU)) != null)
                CustomerMenuController.showMenu(matcher, command);

            else if((matcher = CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.ADD_TO_CART)) != null)
                System.out.println(CustomerMenuController.addToCart(matcher, command));

            else if((matcher = CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.REMOVE_FROM_CART)) != null)
                System.out.println(CustomerMenuController.removeFromCart(matcher, command));

            else if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SHOW_CART) != null)
                CustomerMenuController.showCart();

            else if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SHOW_DISCOUNTS) != null)
                CustomerMenuController.showDiscounts();

            else if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.PURCHASE_CART) != null)
                System.out.println(CustomerMenuController.purchaseCart(command));

            else if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("customer menu");
            else if((matcher = CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SET_LOCATION)) != null)
                System.out.println(CustomerMenuController.setLocation(matcher));
            else
                System.out.println("invalid command!");
        }
    }
}
