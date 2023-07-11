package Model;

public class Food {
    private Restaurant restaurant;
    private String name;
    private String category;
    private int price;
    private int cost;

    public Food(Restaurant restaurant, String name, String category, int price, int cost) {
        this.restaurant = restaurant;
        this.name = name;
        this.category = category;
        this.price = price;
        this.cost = cost;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getCost() {
        return cost;
    }
}
