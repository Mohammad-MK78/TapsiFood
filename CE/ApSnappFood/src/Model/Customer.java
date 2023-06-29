package Model;

import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<Discount> discounts;
    private ArrayList<Order> cart;
    private int debt, location;
    private Delivery delivery;

    public Customer(String username, String password, int location) {
        super(username, password);
        discounts = new ArrayList<>();
        cart = new ArrayList<>();
        debt = 0;
        this.location = location;
        delivery = null;
    }
    public void changeLocation(int location) {
        this.location = location;
    }

    public int getLocation() {
        return location;
    }

    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public Discount getDiscountByCode(String code) {
        for(Discount discount : discounts)
            if(discount.getCode().equals(code))
                return discount;
        return null;
    }

    public ArrayList<Order> getCart() {
        return cart;
    }

    public void addToCart(Order order) {
        cart.add(order);
    }

    public Order getOrderByFood(Food food) {
        for(Order order : cart)
            if(order.getFood().equals(food))
                return order;
        return null;
    }

    public Order getOrderByFoodNameAndRestaurantName(String foodName, String restaurantName) {
        for(Order order : cart)
            if(order.getFood().getName().equals(foodName) &&
                order.getFood().getRestaurant().getUsername().equals(restaurantName))
                return order;
        return null;
    }

    public void removeOrder(Order order) {
        cart.remove(order);
    }

    public int getDebt() {
        return debt;
    }

    public void changeDebt(int amount) {
        this.debt += amount;
    }

    public void removeDiscount(Discount discount) {
        this.discounts.remove(discount);
    }

    public void resetCart() {
        this.cart.clear();
    }
}