package model.user;

import model.Food;

import java.util.ArrayList;

public class Restaurant extends User {
    private ArrayList<Food> foods = new ArrayList<>();
    private String type;

    public Restaurant(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public String getType() {
        return type;
    }

    public boolean IsFoodExist(String name) {
        for (Food food : foods) {
            if (food.getName().equals(name))
                return true;
        }
        return false;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void removeFood(Food food) {
        foods.remove(food);
    }

    public Food getFoodByName(String name) {
        for (Food food : foods) {
            if (food.getName().equals(name))
                return food;
        }
        return null;
    }
}
