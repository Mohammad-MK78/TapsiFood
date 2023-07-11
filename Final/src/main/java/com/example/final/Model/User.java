package com.example.Final.Model;

import java.sql.*;

public class User {
    private String username, password, position, securityQuestion;
    private int credit, location, debt, is_busy;

    public User(String username, String password, int location,String position, String securityQuestion, int credit, int debt, int is_busy) {
        this.username = username;
        this.password = password;
        this.location = location;
        this.position = position;
        this.securityQuestion = securityQuestion;
        this.credit = credit;
        this.debt = debt;
        this.is_busy = is_busy;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPosition() {
        return position;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public int getDebt() {
        return debt;
    }

    public int getIs_busy() {
        return is_busy;
    }

    public void setPassword(String password) throws ClassNotFoundException, SQLException {
        String username = getUsername();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlRegAdmin = "SELECT * FROM tapsifood.accounts where username='" + username + "'";
        ResultSet usernameCheck = statement.executeQuery(sqlRegAdmin);
        if (usernameCheck.next()) {
            String change = "UPDATE tapsifood.accounts SET password='" + password + "' WHERE username='" + username + "'";
            statement.executeUpdate(change);
        }
        this.password = password;
    }

    public int getCredit() {
        return credit;
    }

    public void changeBalance(int amount) throws ClassNotFoundException, SQLException {
        User user = this;
        this.credit += amount;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlRegAdmin = "SELECT * FROM tapsifood.accounts where username='" + user.getUsername() + "'";
        ResultSet usernameCheck = statement.executeQuery(sqlRegAdmin);
        if (usernameCheck.next()) {
            int oldCredit = usernameCheck.getInt("credit");
            String change = "UPDATE tapsifood.accounts SET credit='" + (user.getCredit() + oldCredit) + "' WHERE username='" + user.getUsername() + "'";
            statement.executeUpdate(change);
        }
    }

    public void setLocation(int location) throws ClassNotFoundException, SQLException {
        this.location = location;
        User user = this;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlRegAdmin = "SELECT * FROM tapsifood.accounts where username='" + user.getUsername() + "'";
        ResultSet usernameCheck = statement.executeQuery(sqlRegAdmin);
        if (usernameCheck.next()) {
            String change = "UPDATE tapsifood.accounts SET location='" + user.getLocation() + "' WHERE username='" + user.getUsername() + "'";
            statement.executeUpdate(change);
        }
    }

    public int getLocation() {
        return location;
    }
}
