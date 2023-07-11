package com.example.Final.Controller;

import com.example.Final.Model.*;
import com.example.Final.View.LoginMenuEnums;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {
    public static String setSnappFoodAdmin(String username, String password) throws ClassNotFoundException, SQLException {
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
            SnappFood.setSnappFoodManager(new SnappFoodManager(username, password));
            String sql = "INSERT INTO tapsifood.accounts(position, username, password) VALUES ('admin', '"+username+"', '"+password+"')";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return "TapsiFood admin register successful";
        }
    }

    public static String customerRegister(String username, String password, int location, String securityQuestion) throws ClassNotFoundException, SQLException {

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
        else if (location < 1 || location > 1000) {
            return "register failed: invalid location";
        }
        else {
            String sql = "INSERT INTO tapsifood.accounts(username, password, location, position, security_question) VALUES ('"+username+"', '"+password+"' ,'"+location+"', 'customer', '"+securityQuestion+"')";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            SnappFood.addCustomer(new Customer(username, password, location, securityQuestion, 0, SnappFood.getUserByUsername(username).getDebt()));
            return "customer register successful";
        }
    }
    public static String deliveryRegister(String username, String password, int location, String securityQuestion) throws SQLException, ClassNotFoundException {
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
            String sql = "INSERT INTO tapsifood.accounts(username, password, location, position, security_question) VALUES ('"+username+"', '"+password+"' ,'"+location+"', 'delivery', '"+securityQuestion+"')";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            SnappFood.addDelivery(new Delivery(username, password, location, securityQuestion, 0, SnappFood.getUserByUsername(username).getIs_busy()));
            return "delivery register successful";
        }
    }

    public static String login(String username, String password) throws SQLException, ClassNotFoundException {

         if(SnappFood.getUserByUsername(username) == null)
            return "login failed: username not found";

        else if(!SnappFood.getUserByUsername(username).getPassword().equals(password))
            return "login failed: incorrect password";

        else {
            User user = SnappFood.getUserByUsername(username);
            SnappFood.setCurrentUser(user);
            return user.getPosition();
        }
    }

    public static String changePassword(Matcher matcher) throws SQLException, ClassNotFoundException {
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
    public static String setNewPassword(String username , String newPassword) throws SQLException, ClassNotFoundException {

        if(SnappFood.getUserByUsername(username) == null)
            return "password change failed: username not found";

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
    public static String forgotPassword(String username, String securityQuestion) throws SQLException, ClassNotFoundException {

        if(SnappFood.getUserByUsername(username) == null)
            return "password show failed: username not found";

        else if(!SnappFood.getUserByUsername(username).getSecurityQuestion().toLowerCase().equals(securityQuestion))
            return "password show failed: incorrect security question";

        else {
            return "securityQuestion is ok";
        }
    }

    public static String removeAccount(Matcher matcher) throws SQLException, ClassNotFoundException {
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
