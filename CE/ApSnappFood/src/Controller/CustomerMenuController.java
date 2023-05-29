package Controller;

import Model.*;
import View.CustomerMenuEnums;
import View.SnappFoodAdminMenuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerMenuController {
    private static Customer currentUser;

    public static void setCurrentUser() {
        currentUser = (Customer) SnappFood.getCurrentUser();
    }

    public static String chargeAccount(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));

        if(amount < 1)
            return "charge account failed: invalid cost or price";

        currentUser.changeBalance(amount);
        return "charge account successful";
    }

    public static int showBalance() {
        return currentUser.getBalance();
    }

    public static void showRestaurant(String command) {
        Pattern typePattern = Pattern.compile(CustomerMenuEnums.getString(CustomerMenuEnums.SHOW_RESTAURANT_OPTION));
        Matcher typeMatcher = typePattern.matcher(command);
        int index = 1;

        if(typeMatcher.find()) {
            String type = typeMatcher.group("type");

            for(RestaurantManager restaurant : SnappFood.getRestaurantManagers())
                if(restaurant.getType().equals(type)) {
                    System.out.println(index + ") " + restaurant.getUsername() + ": type=" + restaurant.getType());
                    index++;
                }
        }
        else {
            for(RestaurantManager restaurant : SnappFood.getRestaurantManagers()) {
                System.out.println(index + ") " + restaurant.getUsername() + ": type=" + restaurant.getType());
                index++;
            }
        }
    }

    public static void showMenu(Matcher matcher, String command) {
        String restaurantName = matcher.group("restaurantName");
        Pattern categoryPattern = Pattern.compile(CustomerMenuEnums.getString(CustomerMenuEnums.SHOW_MENU_OPTION));
        Matcher categoryMatcher = categoryPattern.matcher(command);

        if(SnappFood.getRestaurantManagerByUsername(restaurantName) == null) {
            System.out.println("show menu failed: restaurant not found");
            return;
        }

        if(categoryMatcher.find()) {

            String category = categoryMatcher.group("category");

            if(category.equals("starter"))
                for(Food food : SnappFood.getRestaurantManagerByUsername(restaurantName).getStarter())
                    System.out.println(food.getName() + " | price=" + food.getPrice());

            else if(category.equals("entree"))
                for(Food food : SnappFood.getRestaurantManagerByUsername(restaurantName).getEntree())
                    System.out.println(food.getName() + " | price=" + food.getPrice());

            else if(category.equals("dessert"))
                for(Food food : SnappFood.getRestaurantManagerByUsername(restaurantName).getDessert())
                    System.out.println(food.getName() + " | price=" + food.getPrice());

            else
                System.out.println("show menu failed: invalid category");

        }

        else {
            System.out.println("<< STARTER >>");
            for(Food food : SnappFood.getRestaurantManagerByUsername(restaurantName).getStarter())
                System.out.println(food.getName() + " | price=" + food.getPrice());

            System.out.println("<< ENTREE >>");
            for(Food food : SnappFood.getRestaurantManagerByUsername(restaurantName).getEntree())
                System.out.println(food.getName() + " | price=" + food.getPrice());

            System.out.println("<< DESSERT >>");
            for(Food food : SnappFood.getRestaurantManagerByUsername(restaurantName).getDessert())
                System.out.println(food.getName() + " | price=" + food.getPrice());
        }
    }

    public static String addToCart(Matcher matcher, String command) {
        RestaurantManager restaurant = SnappFood.getRestaurantManagerByUsername(matcher.group("restaurantName"));

        if(restaurant == null)
            return "add to cart failed: restaurant not found";

        Food food = restaurant.getFoodByName(matcher.group("foodName"));

        if(food == null)
            return "add to cart failed: food not found";

        Pattern numberPattern = Pattern.compile(CustomerMenuEnums.getString(CustomerMenuEnums.NUMBER_OPTION));
        Matcher numberMatcher = numberPattern.matcher(command);
        int number;

        if(numberMatcher.find()) {
            number = Integer.parseInt(numberMatcher.group("number"));

            if(number < 1)
                return "add to cart failed: invalid number";

        }
        else
            number = 1;

        if(currentUser.getOrderByFood(food) != null)
            currentUser.getOrderByFood(food).changeNumber(number);
        else
            currentUser.addToCart(new Order(food, number, currentUser));

        currentUser.changeDebt(number * food.getPrice());

        return "add to cart successful";
    }

    public static String removeFromCart(Matcher matcher, String command) {
        String foodName = matcher.group("foodName");
        String restaurantName = matcher.group("restaurantName");
        Order order;

        if((order = currentUser.getOrderByFoodNameAndRestaurantName(foodName ,restaurantName)) == null)
            return "remove from cart failed: not in cart";

        Pattern numberPattern = Pattern.compile(CustomerMenuEnums.getString(CustomerMenuEnums.NUMBER_OPTION));
        Matcher numberMatcher = numberPattern.matcher(command);
        int number;

        if(numberMatcher.find()) {
            number = Integer.parseInt(numberMatcher.group("number"));

            if(number < 1)
                return "remove from cart failed: invalid number";

            else if(number > order.getNumber())
                return "remove from cart failed: not enough food in cart";

        }
        else
            number = 1;

        if(order.getNumber() == number)
            currentUser.removeOrder(order);
        else
            order.changeNumber(-number);

        currentUser.changeDebt(-number * order.getFood().getPrice());

        return "remove from cart successful";
    }

    public static void showCart() {
        int index = 1;
        int totalPrice = 0;

        for(Order order : currentUser.getCart()) {
            System.out.println(index + ") " + order);
            totalPrice += order.getNumber() * order.getFood().getPrice();
            index++;
        }

        System.out.println("Total: " + totalPrice);
    }

    public static void showDiscounts() {
        int index = 1;

        for(Discount discount : currentUser.getDiscounts()) {
            System.out.println(index + ") " + discount.getCode() + " | amount=" + discount.getDiscountAmount());
            index++;
        }
    }

    public static String purchaseCart(String command) {
        Matcher discountMatcher = Pattern.compile(CustomerMenuEnums.getString(CustomerMenuEnums.PURCHASE_CART_OPTION)).matcher(command);
        int discountAmount = 0;
        Discount discount = null;

        if(discountMatcher.find()) {

            if((discount = currentUser.getDiscountByCode(discountMatcher.group("discountCode"))) == null)
                return "purchase failed: invalid discount code";

            discountAmount = discount.getDiscountAmount();
        }

        if(currentUser.getBalance() < currentUser.getDebt() - discountAmount)
            return "purchase failed: inadequate money";

        for(Order order : currentUser.getCart())
            order.getFood().getRestaurant().changeBalance(order.getNumber() * (order.getFood().getPrice() - order.getFood().getCost()));

        currentUser.changeBalance(discountAmount - currentUser.getDebt());

        currentUser.changeDebt(-currentUser.getDebt());

        currentUser.resetCart();

        SnappFood.removeDiscount(discount);

        return "purchase successful";
    }
}
