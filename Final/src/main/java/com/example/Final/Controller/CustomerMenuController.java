package com.example.Final.Controller;

import com.example.Final.Model.*;
import com.example.Final.View.CustomerMenuEnums;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerMenuController {
    private static Customer currentUser;

    public static void setCurrentUser() {
        User user = SnappFood.getCurrentUser();
        currentUser = new Customer(user.getUsername(), user.getPassword(), user.getLocation(), user.getSecurityQuestion(), user.getCredit(), user.getDebt());
    }

    public static String chargeAccount(int amount) throws SQLException, ClassNotFoundException {

        if(amount < 1)
            return "Invalid";

        currentUser.changeBalance(amount);
        return "Successful";
    }

    public static int showBalance() {
        return currentUser.getCredit();
    }
    public static String changeLocation(Matcher matcher) throws SQLException, ClassNotFoundException {
        int location = Integer.parseInt(matcher.group("location"));

        if(location < 1 || location > 1000)
            return "change location failed: invalid location";

        currentUser.setLocation(location);
        return "change location successful";
    }
    public static void showRestaurant(String command) throws ClassNotFoundException, SQLException {
        Pattern typePattern = Pattern.compile(CustomerMenuEnums.getString(CustomerMenuEnums.SHOW_RESTAURANT_OPTION));
        Matcher typeMatcher = typePattern.matcher(command);
        if(typeMatcher.find()) {
            String type = typeMatcher.group("type");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();
            String sqlCheckType = "SELECT * FROM tapsifood.restaurants where type='" + type + "'";
            ResultSet typeCheck = statement.executeQuery(sqlCheckType);
            int index = 1;
            while (typeCheck.next()) {
                String name = typeCheck.getString("name");
                Restaurant restaurant = SnappFood.getRestaurantByName(name);
                System.out.println((index++) + ". " + restaurant.getName() + " -> type: " + restaurant.getType() + " | rating: " + restaurant.getRating() +  " | loc: " + restaurant.getLocation());
            }
        }
        else {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();
            String sqlCheckType = "SELECT * FROM tapsifood.restaurants";
            ResultSet typeCheck = statement.executeQuery(sqlCheckType);
            int index = 1;
            while (typeCheck.next()) {
                String name = typeCheck.getString("name");
                Restaurant restaurant = SnappFood.getRestaurantByName(name);
                System.out.println((index++) + ". " + restaurant.getName() + " -> type: " + restaurant.getType() + " | rating: " + restaurant.getRating() +  " | loc: " + restaurant.getLocation());
            }
        }
    }

    public static void showMenu(Matcher matcher, String command) throws SQLException, ClassNotFoundException {
        String restaurantName = matcher.group("restaurantName");
        Pattern categoryPattern = Pattern.compile(CustomerMenuEnums.getString(CustomerMenuEnums.SHOW_MENU_OPTION));
        Matcher categoryMatcher = categoryPattern.matcher(command);
        int restaurantID = 0;
        if(SnappFood.getRestaurantByName(restaurantName) == null) {
            System.out.println("show menu failed: restaurant not found");
            return;
        }
        else { //for check restaurantID
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();
            String sqlCheckRestaurant = "SELECT * FROM tapsifood.restaurants where name='" + restaurantName + "'";
            ResultSet restaurantCheck = statement.executeQuery(sqlCheckRestaurant);
            if (restaurantCheck.next())
                restaurantID = restaurantCheck.getInt("id");
        }

        if(categoryMatcher.find()) {
            String category = categoryMatcher.group("category");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();
            String sqlCheckType = "SELECT * FROM tapsifood.foods where restaurantID='" + restaurantID + "' AND category = '" + category + "'";
            ResultSet typeCheck = statement.executeQuery(sqlCheckType);
            if (typeCheck.next())
                System.out.println("<< " + category + " >>");
            while (typeCheck.next()) {
                String name = typeCheck.getString("name");
                Food food = Restaurant.getFoodByName(name, restaurantName);
                System.out.println(food.getName() + ": price=" + food.getPrice());
            }
        }

        else {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
            Statement statement = connection.createStatement();
            String sqlCheckStarter = "SELECT * FROM tapsifood.foods where restaurantID='" + restaurantID + "' AND category = 'starter'";
            ResultSet StarterCheck = statement.executeQuery(sqlCheckStarter);

            String sqlCheckEntree = "SELECT * FROM tapsifood.foods where restaurantID='" + restaurantID + "' AND category = 'starter'";
            ResultSet EntreeCheck = statement.executeQuery(sqlCheckEntree);

            String sqlCheckDessert = "SELECT * FROM tapsifood.foods where restaurantID='" + restaurantID + "' AND category = 'starter'";
            ResultSet DessertCheck = statement.executeQuery(sqlCheckDessert);

            if (StarterCheck.next())
                System.out.println("<< STARTER >>");
            while (StarterCheck.next()) {
                String name = StarterCheck.getString("name");
                Food food = Restaurant.getFoodByName(name, restaurantName);
                System.out.println(food.getName() + ": price=" + food.getPrice());
            }

            if (EntreeCheck.next())
                System.out.println("<< MAIN MEAL >>");
            while (EntreeCheck.next()) {
                String name = EntreeCheck.getString("name");
                Food food = Restaurant.getFoodByName(name, restaurantName);
                System.out.println(food.getName() + ": price=" + food.getPrice());
            }

            if (DessertCheck.next())
                System.out.println("<< DESSERT >>");
            while (DessertCheck.next()) {
                String name = DessertCheck.getString("name");
                Food food = Restaurant.getFoodByName(name, restaurantName);
                System.out.println(food.getName() + ": price=" + food.getPrice());
            }
        }
    }

    public static String addToCart(Matcher matcher, String command) throws SQLException, ClassNotFoundException {
        Restaurant restaurant = SnappFood.getRestaurantByName(matcher.group("restaurantName"));

        if(restaurant == null)
            return "add to cart failed: restaurant not found";

        Food food = Restaurant.getFoodByName(matcher.group("foodName"), restaurant.getName());

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
        currentUser.getCurrentCart().showCart();
    }

    public static void showDiscounts() {
        int index = 1;
        if (currentUser.getDiscounts().size() == 0) {
            System.out.println("no discount");
            return;
        }
        for(Discount discount : currentUser.getDiscounts()) {
            System.out.println(index + ") " + discount.getCode() + " : amount=" + discount.getDiscountAmount());
            index++;
        }
    }
    public static void show_distance() throws IOException {
        if (currentUser.getCartOrder().size() == 0) {
            System.out.println("there is no ongoing order");
            return;
        }
        int location = currentUser.getCurrentCart().getRestaurant().getLocation(), destination = currentUser.getLocation();
        CityGraph cityGraph = new CityGraph();
        int[][] graph = new int[1001][1001];
        for(int i = 0; i < cityGraph.city.rows; i++) {
            if (cityGraph.city.cols >= 0) System.arraycopy(cityGraph.city.m[i], 0, graph[i], 0, cityGraph.city.cols);
        }
        ShortestPath sp = new ShortestPath(graph);
        int distance = sp.shortestPath(location, destination);
        System.out.println("estimated time : " + distance + " minutes");
    }

    public static String purchaseCart(String command) throws SQLException, ClassNotFoundException {
        Matcher discountMatcher = Pattern.compile(CustomerMenuEnums.getString(CustomerMenuEnums.PURCHASE_CART_OPTION)).matcher(command);
        int discountAmount = 0;
        Discount discount = null;
        if (currentUser.getCartOrder().size() == 0) {
            return "purchase failed: cart is empty";
        }

        if(discountMatcher.find()) {

            if((discount = currentUser.getDiscountByCode(discountMatcher.group("discountCode"))) == null)
                return "purchase failed: invalid discount code";

            discountAmount = discount.getDiscountAmount();
        }

        if(currentUser.getCredit() < currentUser.getDebt() - discountAmount)
            return "purchase failed: inadequate money";

        for (Delivery delivery : SnappFood.getDeliveries()) {
            if (!delivery.is_busy) {
                currentUser.getCurrentCart().setDelivery(delivery);
                delivery.setRestaurant(currentUser.getCurrentCart().getRestaurant().getLocation());
                delivery.setDestination(currentUser.getLocation());
                break;
            }
        }
        for(Order order : currentUser.getCartOrder())
            order.getFood().getRestaurant().changeBalance(order.getNumber() * (order.getFood().getPrice() - order.getFood().getCost()));

        int price = currentUser.getDebt() + currentUser.getDebt() / 5;
        currentUser.changeBalance(discountAmount - price);

        currentUser.changeDebt(-currentUser.getDebt());

        SnappFood.removeDiscount(discount);
        currentUser.getCarts().add(new Cart(currentUser.getCartOrder()));
        currentUser.getCurrentCart().getRestaurant().addCartToOngoings(currentUser.getCurrentCart());
        return "purchase successful";
    }
    public static void showDelivery() {
        if (currentUser.getDelivery() == null) {
            System.out.println("no delivery");
            return;
        }
        System.out.println("delivery name : " + currentUser.getDelivery().getUsername());
    }
    public static String addComment(Matcher matcher) throws SQLException, ClassNotFoundException {
        String message = matcher.group("message");
        String restaurantName = matcher.group("restaurantName");
        if (SnappFood.getRestaurantByName(restaurantName) == null)
            return "restaurant doesn't exist";
        currentUser.addComment(message, restaurantName);
        return "comment added successfully";
    }
    public static void collected() throws SQLException, ClassNotFoundException {
        currentUser.getDelivery().is_busy = false;
        currentUser.getDelivery().setLocation(currentUser.getLocation());
        int totalPrice = 0;
        for (Order order : currentUser.getCartOrder()) {
            totalPrice += order.getNumber() * order.getFood().getPrice();
        }
        currentUser.getDelivery().changeBalance(totalPrice / 5);
        currentUser.getCurrentCart().getRestaurant().addCartToHistory(currentUser.getCurrentCart());
        currentUser.getCurrentCart().getRestaurant().removeFromOngoing(currentUser.getCurrentCart());
        currentUser.resetCart();
        System.out.println("food collected successfully");
    }
    public static String addRating(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate")), orderNumber = Integer.parseInt(matcher.group("orderNumber"));
        if (currentUser.getCarts() == null || orderNumber > currentUser.getCarts().size() || orderNumber < 1)
            return "no cart available";
        currentUser.addRating(rate, orderNumber);
        return "rate added successfully";
    }
    public static void showRestaurantComments(Matcher matcher) throws SQLException, ClassNotFoundException {
        String restaurantName = matcher.group("restaurantName");
        if (SnappFood.getRestaurantByName(restaurantName).getComments().size() == 0)
            System.out.println("no comments");
        else {
            System.out.println("comments :");
            for (String comment : SnappFood.getRestaurantByName(restaurantName).getComments()) {
                System.out.println("{ " + comment + " }");
            }
        }
    }
    public static void showOrderHistory() {
        int index = 1;
        for (Cart cart : currentUser.getCarts()) {
            System.out.println(index + ")");
            cart.showCart();
            index++;
        }
    }
}