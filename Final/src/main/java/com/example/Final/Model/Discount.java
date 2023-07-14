package com.example.Final.Model;

import com.example.Final.Controller.CustomerMenuController;

import java.sql.*;
import java.util.ArrayList;

public class Discount {
    private String code, username;
    private int discountAmount;
    private Customer discountUser;

    public Discount(Customer discountUser, int discountAmount, String code) {
        this.discountUser = discountUser;
        this.discountAmount = discountAmount;
        this.code = code;
        this.username = discountUser.getUsername();
    }

    public static ArrayList<Discount> getCodes() throws ClassNotFoundException, SQLException {
        ArrayList<Discount> discounts = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String getManagerID = "SELECT * FROM tapsifood.accounts where username='" + CustomerMenuController.getCurrentUser().getUsername() + "'";
        int managerID = 0;
        ResultSet getID = statement.executeQuery(getManagerID);
        if (getID.next())
            managerID = getID.getInt("id");
        String sqlCheckType = "SELECT * FROM tapsifood.discount where customerID='" + managerID + "'";
        ResultSet typeCheck = statement.executeQuery(sqlCheckType);
        while (typeCheck.next()) {
            int amount = typeCheck.getInt("amount");
            String code = typeCheck.getString("code");
            Discount discount = new Discount(CustomerMenuController.getCurrentUser(), amount, code);
            discounts.add(discount);
        }
        return discounts;
    }

    public String getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public Customer getDiscountUser() {
        return discountUser;
    }
}
