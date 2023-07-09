package View;

import Controller.CustomerMenuController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class CustomerMenu {

    public static void run(Scanner scanner) throws IOException, SQLException, ClassNotFoundException {
        Matcher matcher;
        String command;

        CustomerMenuController.setCurrentUser();

        while(true) {
            command = scanner.nextLine();

            if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.LOGOUT) != null){
                System.out.println("user logged out successfully");
                return;
            }
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

            else if ((matcher = CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.ADD_RATING)) != null)
                CustomerMenuController.addRating(matcher);
            else if ((matcher = CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.ADD_COMMENT)) != null)
                CustomerMenuController.addComment(matcher);
            else if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SHOW_DISCOUNTS) != null)
                CustomerMenuController.showDiscounts();
            else if (CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SHOW_TIME) != null)
                CustomerMenuController.show_distance();
            else if (CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SHOW_DELIVERY) != null)
                CustomerMenuController.showDelivery();
            else if (CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.COLLECTED) != null)
                CustomerMenuController.collected();
            else if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.PURCHASE_CART) != null)
                System.out.println(CustomerMenuController.purchaseCart(command));

            else if(CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("customer menu");
            else if((matcher = CustomerMenuEnums.getMatcher(command, CustomerMenuEnums.CHANGE_LOCATION)) != null)
                System.out.println(CustomerMenuController.changeLocation(matcher));
            else
                System.out.println("invalid command!");
        }
    }
}
