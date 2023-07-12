package com.example.Final.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RestaurantAdminMenuEnums {
    LOGOUT ("\\s*logout\\s*"),
    SHOW_RESTAURANTS ("\\s*show\\s+restaurant(\\s+-t\\s+(?<type>\\S+))?\\s*"),
    SHOW_RESTAURANT_OPTION ("\\s+-t\\s+(?<type>\\S+)"),
    ENTER_RESTAURANT ("\\s*enter\\s+restaurant\\s+(?<name>\\S+)\\s*"),
    VALID_NAME ("[a-z\\-]+"),
    VALID_TYPE ("[a-z\\-]+"),
    SHOW_CURRENT_MENU ("\\s*show\\s+current\\s+menu\\s*"),
    SHOW_BALANCE ("\\s*show\\s+balance\\s*");

    private String string;

    RestaurantAdminMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(RestaurantAdminMenuEnums restaurantAdminMenuEnums) {
        return restaurantAdminMenuEnums.string;
    }

    public static Matcher getMatcher(String input, RestaurantAdminMenuEnums restaurantAdminMenuEnums) {
        Matcher matcher = Pattern.compile(restaurantAdminMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
