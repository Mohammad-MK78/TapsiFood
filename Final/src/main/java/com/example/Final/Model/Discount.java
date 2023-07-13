package com.example.Final.Model;

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
