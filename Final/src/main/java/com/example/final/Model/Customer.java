package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<Discount> discounts;
    private ArrayList<Cart> carts;
    private Cart currentCart;
    private int debt;

    public Customer(String username, String password, int location, String security_question, int credit, int debt) {
        super(username, password, location, "customer",security_question, credit, debt, 0);
        discounts = new ArrayList<>();
        currentCart = new Cart(new ArrayList<>());
        carts = new ArrayList<>();
        this.debt = debt;
    }

    public Delivery getDelivery() {
        return currentCart.getDelivery();
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
    public Cart getCurrentCart() {
        return currentCart;
    }
    public ArrayList<Order> getCartOrder() {
        return currentCart.getOrders();
    }
    public ArrayList<Cart> getCarts() {
        return carts;
    }
    public void addCart(Cart cart) {
        this.carts.add(cart);
    }

    public void addToCart(Order order) {
        currentCart.addToCart(order);
    }

    public Order getOrderByFood(Food food) {
        for(Order order : currentCart.getOrders())
            if(order.getFood().equals(food))
                return order;
        return null;
    }

    public Order getOrderByFoodNameAndRestaurantName(String foodName, String restaurantName) {
        for(Order order : currentCart.getOrders())
            if(order.getFood().getName().equals(foodName) &&
                order.getFood().getRestaurant().getName().equals(restaurantName))
                return order;
        return null;
    }

    public void removeOrder(Order order) {
        currentCart.removeOrder(order);
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
        this.currentCart.resetCart();
    }
    public void addComment(String message, String restaurantName) throws SQLException, ClassNotFoundException {
        SnappFood.getRestaurantByName(restaurantName).getComments().add(message + " : " + this.getUsername());
    }
    public void addRating(int rate, int orderNumber) {
        this.carts.get(orderNumber-1).getRestaurant().addRating(rate);
    }
}