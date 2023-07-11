package com.example.Final.Model;

import java.util.ArrayList;

public class RestaurantManager extends User{
    private ArrayList<Restaurant> restaurants;

    public RestaurantManager(String username, String password, String security_question, int credit) {
        super(username, password, 0,"manager", security_question, credit, 0, 0);
        this.restaurants = new ArrayList<>();
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public Restaurant getRestaurantByName(String name) {
        for(Restaurant restaurant : restaurants)
            if(restaurant.getName().equals(name))
                return restaurant;
        return null;
    }

    public void removeFood(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }
    public void changeType(String name, String type) {
        for(Restaurant restaurant : restaurants)
            if(restaurant.getName().equals(name))
                restaurant.setType(type);
    }
    @Override
    public String toString() {
        return this.getUsername() + ": Number of restaurants = " + this.restaurants.size() + " | balance = " + this.getCredit();
    }
}
