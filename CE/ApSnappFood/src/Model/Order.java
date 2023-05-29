package Model;

public class Order {
    private Food food;
    private int number;
    private Customer customer;

    public Order(Food food, int number, Customer customer) {
        this.food = food;
        this.number = number;
        this.customer = customer;
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
        return this.food.getName() + " | restaurant=" + this.food.getRestaurant().getUsername() + " price=" + this.number * this.food.getPrice();
    }
}