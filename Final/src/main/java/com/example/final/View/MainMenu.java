package View;
import Model.SnappFood;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class MainMenu {
    public static void run(Scanner scanner) throws IOException, SQLException, ClassNotFoundException {
        String position = SnappFood.getCurrentUser().getPosition();
        switch (position) {
            case "customer":
                System.out.println("enter menu successful: You are in the customer menu!");
                CustomerMenu.run(scanner);
                return;
            case "manager":
                System.out.println("enter menu successful: You are in the restaurant admin menu!");
                RestaurantAdminMenu.run(scanner);
                return;
            case "admin":
                System.out.println("enter menu successful: You are in the Snappfood admin menu!");
                SnappFoodAdminMenu.run(scanner);
                return;
            case "delivery":
                System.out.println("enter menu successful: You are in the delivery menu!");
                DeliveryMenu.run(scanner);
        }
    }
}
