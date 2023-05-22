package controller;

import model.user.App;
import model.user.Customer;
import model.user.Restaurant;
import view.enums.commands.LoginMenuCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {
    public static String login(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        if (App.getUserByUsername(username) == null)
            return "login failed: username not found";
        else if (!App.getUserByUsername(username).isPasswordCorrect(password))
            return "login failed: incorrect password";
        else {
            App.setCurrentUser(username);
            return "login successful";
        }
    }

    public static String register(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        if (!(Pattern.compile("[a-z]").matcher(username).find()
                || Pattern.compile("[A-Z]").matcher(username).find())
                || (LoginMenuCommands.getMatcher(username, LoginMenuCommands.VALID_USERNAME) == null))
            return "register failed: invalid username format";
        else if (App.getUserByUsername(username) != null)
            return "register failed: username already exists";
        else if (LoginMenuCommands.getMatcher(password, LoginMenuCommands.VALID_USERNAME) == null)
            return "register failed: invalid password format";
        else if (password.length() < 5
                || !Pattern.compile("[A-Z]").matcher(password).find()
                || !Pattern.compile("[a-z]").matcher(password).find()
                || !Pattern.compile("\\d").matcher(password).find())
            return "register failed: weak password";
        else {
            Customer customer = new Customer(username, password);
            App.addCustomer(customer);
            App.addUser(customer);
            return "register successful";
        }
    }

    public static void changePassword(Matcher matcher) {
        String username = matcher.group("username");
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        if (App.getUserByUsername(username) == null)
            System.out.println("password change failed: username not found");
        else if (!App.getUserByUsername(username).isPasswordCorrect(oldPassword))
            System.out.println("password change failed: incorrect password");
        else if (LoginMenuCommands.getMatcher(newPassword, LoginMenuCommands.VALID_USERNAME) == null)
            System.out.println("password change failed: invalid new password");
        else if (newPassword.length() < 5
                || !Pattern.compile("[A-Z]").matcher(newPassword).find()
                || !Pattern.compile("[a-z]").matcher(newPassword).find()
                || !Pattern.compile("\\d").matcher(newPassword).find())
            System.out.println("password change failed: weak new password");
        else {
            App.getUserByUsername(username).setPassword(newPassword);
            System.out.println("password change successful");
        }
    }

    public static void removeAccount(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(App.getUserByUsername(username) == null)
            System.out.println("remove account failed: username not found");
        else if(!App.getUserByUsername(username).isPasswordCorrect(password))
            System.out.println("remove account failed: incorrect password");
        else{
            if(App.getUserByUsername(username) instanceof Restaurant)
                App.removeRestaurant((Restaurant)App.getUserByUsername(username));
            else
                App.removeCustomer((Customer)App.getUserByUsername(username));
            System.out.println("remove account successful");
        }
    }
}
