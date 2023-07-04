package View;

import Controller.LoginMenuController;
import com.sun.tools.javac.Main;
import java.sql.*;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    public static void run(Scanner scanner) throws IOException, SQLException, ClassNotFoundException {
        Matcher matcher;
        String command;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlRegAdmin = "SELECT * FROM tapsifood.accounts where id='" + 0 + "'";
        ResultSet regAdminCheck = statement.executeQuery(sqlRegAdmin);
        String username, password;
        if (!regAdminCheck.next()){
            System.out.println("TapsiFood admin register:");
            String regCheck = "";
            while (!regCheck.equals("TapsiFood admin register successful")) {
                username = LoginMenuEnums.getMatcher(scanner.nextLine(), LoginMenuEnums.SNAPP_FOOD_ADMIN_INPUT).group("input");
                password = LoginMenuEnums.getMatcher(scanner.nextLine(), LoginMenuEnums.SNAPP_FOOD_ADMIN_INPUT).group("input");
                regCheck = LoginMenuController.setSnappFoodAdmin(username, password);
                System.out.println(regCheck);
            }
        }
        System.out.println("Welcome:)");
        while (true) {
            command = scanner.nextLine();

            if(LoginMenuEnums.getMatcher(command, LoginMenuEnums.EXIT) != null){
                System.out.println("Bye Bye <3");
                return;
            }

            else if((matcher = LoginMenuEnums.getMatcher(command, LoginMenuEnums.CUSTOMER_REGISTER)) != null)
                System.out.println(LoginMenuController.register(matcher));
            else if((matcher = LoginMenuEnums.getMatcher(command, LoginMenuEnums.DELIVERY_REGISTER)) != null)
                System.out.println(LoginMenuController.deliveryRegister(matcher));
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
