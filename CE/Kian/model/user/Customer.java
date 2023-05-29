package model.user;

import model.Discount;
import model.Food;
import model.Order;

import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Discount> discounts = new ArrayList<>();

    public Customer(String username, String password) {
        super(username, password);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public void removeDiscountByCode(String code) {
        for (Discount discount : discounts) {
            if(discount.getCode().equals(code)){
                discounts.remove(discount);
                return;
            }
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order getOrderByFood(Food food) {
        for (Order order : orders) {
            if (order.getFood().equals(food))
                return order;
        }
        return null;
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public int getAmountByCode(String code) {
        for (Discount discount : discounts) {
            if (discount.getCode().equals(code))
                return discount.getAmount();
        }
        return 0;
    }

    public boolean isDiscountExist(String code) {
        for (Discount discount : discounts) {
            if (discount.getCode().equals(code))
                return true;
        }
        return false;
    }

    public void eraseOrder(){
        orders.clear();
    }
}
