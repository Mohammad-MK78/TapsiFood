package view;

import controller.LoginMenuController;
import view.enums.commands.LoginMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    public void run(Scanner scanner) {
        String command;
        Matcher matcher;
        while (true) {
            command = scanner.nextLine();
            if (LoginMenuCommands.getMatcher(command, LoginMenuCommands.EXIT) != null)
                return;
            else if (LoginMenuCommands.getMatcher(command, LoginMenuCommands.SHOW_MENU) != null)
                System.out.println("login menu");
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.LOGIN)) != null) {
                String result = LoginMenuController.login(matcher);
                System.out.println(result);
                if (result.equals("login successful"))
                    new MainMenu().run(scanner);
            } else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.REGISTER)) != null)
                System.out.println(LoginMenuController.register(matcher));
            else if((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.CHANGE_PASSWORD)) != null)
                LoginMenuController.changePassword(matcher);
            else if((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.REMOVE_ACCOUNT)) != null)
                LoginMenuController.removeAccount(matcher);
            else
                System.out.println("invalid command!");
        }
    }
}