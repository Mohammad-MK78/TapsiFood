package Model;

import java.util.ArrayList;

public class RestaurantManager extends User{
    private String type;
    private ArrayList<Food> menu;
    private ArrayList<Food> starterMenu;
    private ArrayList<Food> entreeMenu;
    private ArrayList<Food> dessertMenu;

    public RestaurantManager(String username, String password, String type) {
        super(username, password);
        this.type = type;
        menu = new ArrayList<>();
        starterMenu = new ArrayList<>();
        entreeMenu = new ArrayList<>();
        dessertMenu = new ArrayList<>();
    }

    public String getType() {
        return type;
    }


    public ArrayList<Food> getStarter() {
        return starterMenu;
    }

    public ArrayList<Food> getEntree() {
        return entreeMenu;
    }

    public ArrayList<Food> getDessert() {
        return dessertMenu;
    }

    public void addFood(Food food) {
        menu.add(food);

        switch (food.getCategory()) {
            case "starter":
                starterMenu.add(food);
                break;
            case "entree":
                entreeMenu.add(food);
                break;
            case "dessert":
                dessertMenu.add(food);
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
            case "starter":
                starterMenu.remove(food);
                break;
            case "entree":
                entreeMenu.remove(food);
                break;
            case "dessert":
                dessertMenu.remove(food);
        }
    }

    @Override
    public String toString() {
        return this.getUsername() + ": type=" + type + " balance=" + this.getBalance();
    }
}
