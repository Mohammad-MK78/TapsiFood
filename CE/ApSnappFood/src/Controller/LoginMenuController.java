package Controller;

import Model.Customer;
import Model.SnappFood;
import Model.SnappFoodManager;
import View.LoginMenuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {

    public static void setSnappFoodAdmin(String username, String password) {
        SnappFood.setSnappFoodManager(new SnappFoodManager(username, password));
    }

    public static String register(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        if(LoginMenuEnums.getMatcher(username, LoginMenuEnums.VALID_USERNAME) == null)
            return "register failed: invalid username format";

        else if(SnappFood.getUserByUsername(username) != null)
            return "register failed: username already exists";

        else if(LoginMenuEnums.getMatcher(password, LoginMenuEnums.VALID_PASSWORD) == null)
            return "register failed: invalid password format";

        else if(password.length() < 5 ||
                !Pattern.compile("[a-z]").matcher(password).find() ||
                !Pattern.compile("[A-Z]").matcher(password).find() ||
                !Pattern.compile("\\d").matcher(password).find())
            return "register failed: weak password";

        else {
            SnappFood.addCustomer(new Customer(username, password));
            return "register successful";
        }
    }

    public static String login(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        if(SnappFood.getUserByUsername(username) == null)
            return "login failed: username not found";

        else if(!SnappFood.getUserByUsername(username).getPassword().equals(password))
            return "login failed: incorrect password";

        else {
            SnappFood.setCurrentUser(SnappFood.getUserByUsername(username));
            return "login successful";
        }
    }

    public static String changePassword(Matcher matcher) {
        String username = matcher.group("username");
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");

        if(SnappFood.getUserByUsername(username) == null)
            return "password change failed: username not found";

        else if(!SnappFood.getUserByUsername(username).getPassword().equals(oldPassword))
            return "password change failed: incorrect password";

        else if(LoginMenuEnums.getMatcher(newPassword, LoginMenuEnums.VALID_PASSWORD) == null)
            return "password change failed: invalid new password";

        else if(newPassword.length() < 5 ||
                !Pattern.compile("[a-z]").matcher(newPassword).find() ||
                !Pattern.compile("[A-Z]").matcher(newPassword).find() ||
                !Pattern.compile("\\d").matcher(newPassword).find())
            return "password change failed: weak new password";

        else {
            SnappFood.getUserByUsername(username).setPassword(newPassword);
            return "password change successful";
        }
    }

    public static String removeAccount(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        if(SnappFood.getUserByUsername(username) == null)
            return "remove account failed: username not found";

        else if(!SnappFood.getUserByUsername(username).getPassword().equals(password))
            return "remove account failed: incorrect password";

        else {
            SnappFood.removeUser(SnappFood.getUserByUsername(username));
            return "remove account successful";
        }
    }
}
