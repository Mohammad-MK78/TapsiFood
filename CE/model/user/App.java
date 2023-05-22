package model.user;

import model.Discount;

import java.util.ArrayList;

public class App {
    private static ArrayList<Restaurant> restaurants = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;
    private static SnappfoodAdmin snappfoodAdmin;
    private static ArrayList<Discount> discounts = new ArrayList<>();

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public static void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public static void setCurrentUser(String username) {
        App.currentUser = getUserByUsername(username);
    }

    public static ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }


    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void removeRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
        users.remove(restaurant);
    }

    public static void removeCustomer(Customer customer) {
        customers.remove(customer);
        users.remove(customer);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static SnappfoodAdmin getSnappfoodAdmin() {
        return snappfoodAdmin;
    }

    public static Customer getCustomerByUsername(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username))
                return customer;
        }
        return null;
    }

    public static void setSnappfoodAdmin(String username, String password){
        snappfoodAdmin = new SnappfoodAdmin(username, password);
    }

    public static void removeDiscountByCode(String code) {
        for (Discount discount : discounts) {
            if(discount.getCode().equals(code)){
                discounts.remove(discount);
                return;
            }
        }
    }
}
