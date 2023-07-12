package com.example.Final.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RestaurantMenuEnums {
    BACK ("\\s*logout\\s*"),

    SHOW_CURRENT_MENU ("\\s*show\\s+current\\s+menu\\s*"),
    VALID_FOOD_NAME ("[a-z\\-]+"),
    SHOW_BALANCE ("\\s*show\\s+balance\\s*"),
    SHOW_ORDER_HISTORY ("\\s*show\\s+history\\s*"),
    SHOW_ONGOING_ORDERS ("\\s*show\\s+ongoing\\s*"),
    SHOW_COMMENTS ("\\s*show\\s+comments\\s*"),
    REPLY_TO_COMMENT ("\\s*reply\\s+to\\s+comment\\s+(?<commentNum>\\S+)\\s*(?<comment>)");
    private String string;

    RestaurantMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(RestaurantMenuEnums restaurantMenuEnums) {
        return restaurantMenuEnums.string;
    }

    public static Matcher getMatcher(String input, RestaurantMenuEnums restaurantMenuEnums) {
        Matcher matcher = Pattern.compile(restaurantMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
