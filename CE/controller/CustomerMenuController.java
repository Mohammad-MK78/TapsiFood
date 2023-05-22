package controller;

import model.Discount;
import model.Food;
import model.Order;
import model.user.App;
import model.user.Customer;
import model.user.Restaurant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerMenuController {
    public static void showRestaurant(Matcher matcher, String command) {
        int i = 1;
        if (!Pattern.compile("(\\s+-t\\s+(?<type>\\S+))").matcher(command).find()) {
            for (Restaurant restaurant : App.getRestaurants()) {
                System.out.println(i + ") " + restaurant.getUsername() + ": type=" + restaurant.getType());
                i++;
            }
        } else {
            String type = matcher.group("type");
            for (Restaurant restaurant : App.getRestaurants()) {
                if (restaurant.getType().equals(type)) {
                    System.out.println(i + ") " + restaurant.getUsername() + ": type=" + restaurant.getType());
                    i++;
                }
            }
        }
    }

    public static void showMenu(Matcher matcher, String command) {
        String name = matcher.group("name");
        Restaurant restaurant = (Restaurant) App.getUserByUsername(name);
        if (restaurant == null) {
            System.out.println("show menu failed: restaurant not found");
        } else if (Pattern.compile("(\\s+-c\\s+(?<category>\\S+))").matcher(command).find()) {
            String category = matcher.group("category");
            if (!category.equals("starter")
                    && !category.equals("entree")
                    && !category.equals("dessert")) {
                System.out.println("show menu failed: invalid category");
            } else {
                for (Food food : restaurant.getFoods()) {
                    if (food.getCategory().equals(category)) {
                        System.out.println(food.getName() + " | price=" + food.getPrice());
                    }
                }
            }
        } else {
            System.out.println("<< STARTER >>");
            for (Food food : restaurant.getFoods()) {
                if (food.getCategory().equals("starter")) {
                    System.out.println(food.getName() + " | price=" + food.getPrice());
                }
            }
            System.out.println("<< ENTREE >>");
            for (Food food : restaurant.getFoods()) {
                if (food.getCategory().equals("entree")) {
                    System.out.println(food.getName() + " | price=" + food.getPrice());
                }
            }
            System.out.println("<< DESSERT >>");
            for (Food food : restaurant.getFoods()) {
                if (food.getCategory().equals("dessert")) {
                    System.out.println(food.getName() + " | price=" + food.getPrice());
                }
            }
        }
    }

    public static void addToCart(Matcher matcher, String command) {
        int number;
        Order order;
        String restaurantName = matcher.group("restaurantName");
        Restaurant restaurant = (Restaurant) App.getUserByUsername(restaurantName);
        if (restaurant == null)
            System.out.println("add to cart failed: restaurant not found");
        else {
            String foodName = matcher.group("foodName");
            Food food = restaurant.getFoodByName(foodName);
            if (food == null)
                System.out.println("add to cart failed: food not found");
            else {
                order = ((Customer)App.getCurrentUser()).getOrderByFood(food);
                if (Pattern.compile("(\\s+-n\\s+(?<number>\\S+))").matcher(command).find()) {
                    number = Integer.parseInt(matcher.group("number"));
                    if (number <= 0)
                        System.out.println("add to cart failed: invalid number");
                    else {
                        if(order == null) {
                            order = new Order(food, number);
                            ((Customer) App.getCurrentUser()).addOrder(order);
                        }
                        else
                            order.addCount(number);
                        System.out.println("add to cart successful");
                    }
                } else {
                    if(order == null) {
                        order = new Order(food, 1);
                        ((Customer) App.getCurrentUser()).addOrder(order);
                    }
                    else
                        order.addCount(1);
                    System.out.println("add to cart successful");
                }
            }
        }
    }

    public static void removeFromCart(Matcher matcher, String command) {
        int number;
        Food food;
        String restaurantName = matcher.group("restaurantName");
        String foodName = matcher.group("foodName");
        Restaurant restaurant = (Restaurant) App.getUserByUsername(restaurantName);
        if (restaurant == null || ((Customer) App.getCurrentUser()).getOrderByFood(restaurant.getFoodByName(foodName)) == null)
            System.out.println("remove from cart failed: not in cart");
        else {
            food = restaurant.getFoodByName(foodName);
            Order order = ((Customer) App.getCurrentUser()).getOrderByFood(food);
            if (Pattern.compile("(\\s+-n\\s+(?<number>\\S+))").matcher(command).find()) {
                number = Integer.parseInt(matcher.group("number"));
                if (number <= 0)
                    System.out.println("remove from cart failed: invalid number");
                else if (number > order.getCount())
                    System.out.println("remove from cart failed: not enough food in cart");
                else {
                    if (number == order.getCount())
                        ((Customer) App.getCurrentUser()).removeOrder(order);
                    else
                        order.reduceCount(number);
                    System.out.println("remove from cart successful");
                }
            } else {
                if (order.getCount() == 1)
                    ((Customer) App.getCurrentUser()).removeOrder(order);
                else
                    order.reduceCount(1);
                System.out.println("remove from cart successful");
            }
        }
    }

    public static void showCart() {
        int totalPrice = 0;
        int price;
        int i = 1;
        for (Order order : ((Customer) App.getCurrentUser()).getOrders()) {
            price = order.getCount() * order.getFood().getPrice();
            System.out.println(i + ") " + order.getFood().getName() + " | restaurant=" + order.getFood().getRestaurant().getUsername() + " price=" + price);
            totalPrice += price;
            i++;
        }
        System.out.println("Total: " + totalPrice);
    }

    public static void showDiscounts() {
        int i = 1;
        for (Discount discount : ((Customer) App.getCurrentUser()).getDiscounts()) {
            System.out.println(i + ") " + discount.getCode() + " | amount=" + discount.getAmount());
            i++;
        }
    }

    public static void purchaseCart(Matcher matcher, String command) {
        int total = totalPrice();
        if (Pattern.compile("(\\s+-d\\s+(?<code>\\S+))").matcher(command).find()) {
            String code = matcher.group("code");
            if (!((Customer) App.getCurrentUser()).isDiscountExist(code)) {
                System.out.println("purchase failed: invalid discount code");
                return;
            }
            else {
                total -= ((Customer) App.getCurrentUser()).getAmountByCode(code);
                if(total <= App.getCurrentUser().getBalance()) {
                    ((Customer) App.getCurrentUser()).removeDiscountByCode(code);
                    App.removeDiscountByCode(code);
                }
            }
        }
        if (total > App.getCurrentUser().getBalance())
            System.out.println("purchase failed: inadequate money");
        else {
            System.out.println("purchase successful");
            App.getCurrentUser().reduceBalance(total);
            for (Order order : ((Customer) App.getCurrentUser()).getOrders()) {
                order.getFood().getRestaurant().addBalance(order.getFood().getPrice() * order.getCount());
                order.getFood().getRestaurant().reduceBalance(order.getFood().getCost() * order.getCount());
            }
            ((Customer)App.getCurrentUser()).eraseOrder();
        }

    }

    public static int totalPrice() {
        int totalPrice = 0;
        int price;
        for (Order order : ((Customer) App.getCurrentUser()).getOrders()) {
            price = order.getCount() * order.getFood().getPrice();
            totalPrice += price;
        }
        return totalPrice;
    }
}
