package model;

import model.user.User;

public class Discount {
    private User owner;
    private String code;
    private int amount;

    public Discount(User owner, String code, int amount) {
        this.owner = owner;
        this.code = code;
        this.amount = amount;
    }

    public User getOwner() {
        return owner;
    }

    public String getCode() {
        return code;
    }

    public int getAmount() {
        return amount;
    }


}
