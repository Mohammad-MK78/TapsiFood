package View;

import Controller.LoginMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    public static void run(Scanner scanner) {
        Matcher matcher;
        String command;

        String username = LoginMenuEnums.getMatcher(scanner.nextLine(), LoginMenuEnums.SNAPP_FOOD_ADMIN_INPUT).group("input");
        String password = LoginMenuEnums.getMatcher(scanner.nextLine(), LoginMenuEnums.SNAPP_FOOD_ADMIN_INPUT).group("input");

        LoginMenuController.setSnappFoodAdmin(username, password);

        while (true) {
            command = scanner.nextLine();

            if(LoginMenuEnums.getMatcher(command, LoginMenuEnums.EXIT) != null) return;

            else if((matcher = LoginMenuEnums.getMatcher(command, LoginMenuEnums.REGISTER)) != null)
                System.out.println(LoginMenuController.register(matcher));

            else if((matcher = LoginMenuEnums.getMatcher(command, LoginMenuEnums.LOGIN)) != null) {
                String result = LoginMenuController.login(matcher);

                System.out.println(result);

                if(result.equals("login successful"))
                    MainMenu.run(scanner);
            }

            else if((matcher = LoginMenuEnums.getMatcher(command, LoginMenuEnums.CHANGE_PASSWORD)) != null)
                System.out.println(LoginMenuController.changePassword(matcher));

            else if((matcher = LoginMenuEnums.getMatcher(command, LoginMenuEnums.REMOVE_ACCOUNT)) != null)
                System.out.println(LoginMenuController.removeAccount(matcher));

            else if(LoginMenuEnums.getMatcher(command, LoginMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("login menu");

            else
                System.out.println("invalid command!");
        }
    }
}
