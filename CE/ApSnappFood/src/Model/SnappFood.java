package Model;

import java.util.ArrayList;

public class SnappFood {
    private static User currentUser;
    private static ArrayList<User> users = new ArrayList<>();
    private static SnappFoodManager snappFoodManager;
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<RestaurantManager> restaurantManagers = new ArrayList<>();
    private static ArrayList<Delivery> deliveries = new ArrayList<>();
    private static ArrayList<Discount> discounts = new ArrayList<>();

    public static void addDiscount(Discount discount) {
        discounts.add(discount);
        discount.getDiscountUser().addDiscount(discount);
    }
    public static ArrayList<Discount> getDiscounts() {
        return discounts;
    }
    public static Discount getDiscountByCode(String code) {
        for(Discount discount : discounts)
            if(discount.getCode().equals(code))
                return discount;
        return null;
    }
    public static ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }
    public static ArrayList<RestaurantManager> getRestaurantManagers() {
        return restaurantManagers;
    }
    public static void removeUser(User user) {
        users.remove(user);
        if(user instanceof Customer)
            customers.remove(user);
        else
            restaurantManagers.remove(user);
    }

    public static void setSnappFoodManager(SnappFoodManager snappFoodManager) {
        SnappFood.snappFoodManager = snappFoodManager;
        users.add(snappFoodManager);
    }

    public static void addCustomer(Customer customer) {
        customers.add(customer);
        users.add(customer);
    }

    public static void addRestaurantManager(RestaurantManager restaurantManager) {
        restaurantManagers.add(restaurantManager);
        users.add(restaurantManager);
    }
    public static void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
        users.add(delivery);
    }

    public static User getUserByUsername(String username) {
        for(User user : users)
            if(user.getUsername().equals(username)) return user;
        return null;
    }

    public static Customer getCustomerByUsername(String username) {
        for(Customer customer : customers)
            if(customer.getUsername().equals(username)) return customer;
        return null;
    }

    public static RestaurantManager getRestaurantManagerByUsername(String username) {
        for(RestaurantManager restaurantManager : restaurantManagers)
            if (restaurantManager.getUsername().equals(username)) return restaurantManager;
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SnappFood.currentUser = currentUser;
    }

    public static void removeDiscount(Discount discount) {
        if(discount != null) {
            discounts.remove(discount);
            ((Customer) currentUser).removeDiscount(discount);
        }
    }
}
