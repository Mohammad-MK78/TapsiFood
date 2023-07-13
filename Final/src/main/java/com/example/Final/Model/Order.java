package com.example.Final.Model;
public class Order {
    private Food food;
    private String name;
    private int number, price, totalPrice;
    private Customer customer;

    public Order(Food food, int number, Customer customer) {
        this.food = food;
        this.number = number;
        this.customer = customer;
        this.name = food.getName();
        this.price = food.getPrice();
        this.totalPrice = this.price * this.number;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getTotalPrice() {
        return totalPrice;
    }

    public Food getFood() {
        return food;
    }

    public int getNumber() {
        return number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void changeNumber(int amount) {
        number += amount;
    }

    @Override
    public String toString() {
        return this.food.getName() + " | restaurant=" + this.food.getRestaurant().getName() + " price=" + this.number * this.food.getPrice();
    }
}
