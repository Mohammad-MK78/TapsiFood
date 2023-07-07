package Model;

public class Delivery extends User{
    private int restaurant, destination;
    public boolean is_busy;
    public Delivery(String username, String password, int location, String security_question) {
        super(username, password, location, security_question);
        this.is_busy = false;
    }
    public int getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(int restaurant) {
        this.restaurant = restaurant;
    }
    public void setDestination(int destination) {
        this.destination = destination;
    }
    public int getDestination() {
        return this.destination;
    }

}