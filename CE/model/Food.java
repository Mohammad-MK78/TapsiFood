package model;

import model.user.Restaurant;

public class Food {
    private String name;
    private Restaurant restaurant;
    private int price;
    private int cost;

    private String category;

    public Food(String name, String category, Restaurant restaurant, int price, int cost) {
        this.name = name;
        this.category = category;
        this.restaurant = restaurant;
        this.price = price;
        this.cost = cost;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public int getPrice(){
        return price;
    }

    public Restaurant getRestaurant(){
        return restaurant;
    }

    public int getCost() {
        return cost;
    }
}