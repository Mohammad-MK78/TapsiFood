package Model;

public class Delivery extends User{
    private int location, restaurant, destination;
    public Delivery(String username, String password, int location) {
        super(username, password);
        this.location = location;
    }
    public void setLocation(int location) {
        this.location = location;
    }
    public int getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(int restaurant) {
        this.restaurant = restaurant;
    }
    public int getLocation() {
        return this.location;
    }
    public void setDestination(int destination) {
        this.destination = destination;
    }
    public int getDestination() {
        return this.destination;
    }

}