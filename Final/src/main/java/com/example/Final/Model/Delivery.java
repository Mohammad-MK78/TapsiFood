package com.example.Final.Model;

public class Delivery extends User{
    private int destination;
    public boolean is_busy;
    public Delivery(String username, String password, int location, String securityQuestion, int credit, int is_busy) {
        super(username, password, location, "delivery", securityQuestion, credit, 0, is_busy);
        this.is_busy = is_busy != 0;
    }
    public void setDestination(int destination) {
        this.destination = destination;
    }
    public int getDestination() {
        return this.destination;
    }

}