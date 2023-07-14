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
        if (user == null)
            currentUser = null;
        else
            currentUser = new Customer(user.getUsername(), user.getPassword(), user.getLocation(), user.getSecurityQuestion(), user.getCredit(), user.getDebt());
    }

    public static Customer getCurrentUser() {
        return currentUser;
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
    public static String changeLocation(int location) throws SQLException, ClassNotFoundException {
        if (location > 1000 || location < 1) {
            return "Invalid!";
        }
        currentUser.setLocation(location);
        return "successful!";
    }
    public static int getLocation() {
        return currentUser.getLocation();
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
    public static void show_distance() throws IOException, SQLException, ClassNotFoundException {
        if (currentUser.getCartOrder().size() == 0) {
            System.out.println("there is no ongoing order");
            return;
        }
        ShortestPath shortestPath = new ShortestPath(DeliveryMenuController.getCityGraph());
        int distance = shortestPath.shortestPath(currentUser.getCurrentCart().getDelivery().getLocation(), currentUser.getCurrentCart().getRestaurant().getLocation()) + shortestPath.shortestPath(currentUser.getCurrentCart().getRestaurant().getLocation(), currentUser.getLocation());
        System.out.println("estimated time : " + distance + " minutes");
    }

    public static String purchaseCart(String code) throws SQLException, ClassNotFoundException {
        int discountAmount = 0;
        Discount discount = null;
        if (currentUser.getCartOrder().size() == 0) {
            return "purchase failed: cart is empty";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();

        String getCustomerID = "SELECT * FROM tapsifood.accounts where username='" + currentUser.getUsername() + "'";
        int customerID = 0;
        ResultSet getCID = statement.executeQuery(getCustomerID);
        if (getCID.next())
            customerID = getCID.getInt("id");
        if (!code.equals(" ")) {
            String getDiscount = "SELECT * FROM tapsifood.discount where customerID='" + customerID + "' AND code='" + code + "'";
            ResultSet discountExist = statement.executeQuery(getDiscount);
            if (discountExist.next()) {
                discountAmount = discountExist.getInt("amount");
                discount = new Discount(currentUser, discountAmount, code);
            } else
                return "purchase failed: invalid discount code";
        }
        currentUser.setCurrentCart();
        int cartPrice = currentUser.getCurrentCart().getTotalPrice();
        if(currentUser.getCredit() < cartPrice - discountAmount)
            return "purchase failed: inadequate money";

        Delivery cartDelivery = null;
        for (Delivery delivery : SnappFood.getDeliveries()) { //TODO: Choose Delivery
            if (!delivery.is_busy) {
                currentUser.getCurrentCart().setDelivery(delivery);
                delivery.setLocation(currentUser.getCurrentCart().getRestaurant().getLocation());
                delivery.setDestination(currentUser.getLocation());
                cartDelivery = delivery;
                break;
            }
        }
        for(Order order : currentUser.getCartOrder())
            order.getFood().getRestaurant().changeBalance(order.getNumber() * (order.getFood().getPrice() - order.getFood().getCost()));
        int PPM = 1; //price per meter
        int deliveryPrice = Math.abs(cartDelivery.getLocation() - currentUser.getLocation()) * PPM;
        int totalPrice = cartPrice + deliveryPrice - discountAmount;
        if(currentUser.getCredit() < totalPrice)
            return "purchase failed: inadequate money";

        cartDelivery.changeBalance(deliveryPrice);
        if (totalPrice < 0)
            totalPrice = 0;
        currentUser.changeBalance(-1 * totalPrice);

        SnappFood.removeDiscount(discount);
        currentUser.finishPurchase(currentUser.getCurrentCart());
//        currentUser.getCarts().add(new Cart(currentUser.getCartOrder()));//Todo: add cart to customer history
        currentUser.getCurrentCart().getRestaurant().addCartToOngoings(currentUser.getCurrentCart()); //Todo: add cart to restaurant on going
//        currentUser.getCurrentCart().resetCart(); //TODO: clear customer cart
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
    public static void collected() throws SQLException, ClassNotFoundException { //TODO: collect order
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