package Model;

import java.util.ArrayList;

public class RestaurantManager extends User{
    private String type;
    private int location;
    private ArrayList<Food> menu;
    private ArrayList<Food> Starter;
    private ArrayList<Food> MainMeal;
    private ArrayList<Food> Dessert;

    public RestaurantManager(String username, String password, String type, int location) {
        super(username, password);
        this.type = type;
        this.location = location;
        menu = new ArrayList<>();
        Starter = new ArrayList<>();
        MainMeal = new ArrayList<>();
        Dessert = new ArrayList<>();
    }
    public int getLocation() {
         return location;
    }
    public String getType() {
        return type;
    }
    public ArrayList<Food> getStarter() {
        return Starter;
    }

    public ArrayList<Food> getEntree() {
        return MainMeal;
    }

    public ArrayList<Food> getDessert() {
        return Dessert;
    }

    public void addFood(Food food) {
        menu.add(food);
        switch (food.getCategory()) {
            case "Starter":
                Starter.add(food);
                break;
            case "MainMeal":
                MainMeal.add(food);
                break;
            case "Dessert":
                Dessert.add(food);
        }
    }

    public Food getFoodByName(String name) {
        for(Food food : menu)
            if(food.getName().equals(name))
                return food;
        return null;
    }

    public void removeFood(Food food) {
        menu.remove(food);

        switch (food.getCategory()) {
            case "Starter":
                Starter.remove(food);
                break;
            case "MainMeal":
                MainMeal.remove(food);
                break;
            case "Dessert":
                Dessert.remove(food);
        }
    }
    public void chengeType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return this.getUsername() + ": type = " + type + " | balance = " + this.getBalance();
    }
}
