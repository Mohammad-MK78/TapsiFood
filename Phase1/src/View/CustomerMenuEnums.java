package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CustomerMenuEnums {
    LOGOUT ("\\s*logout\\s*"),
    SHOW_CURRENT_MENU ("\\s*show\\s+current\\s+menu\\s*"),
    CHARGE_ACCOUNT ("\\s*charge\\s+account\\s+(?<amount>-?\\d+)\\s*"),
    SHOW_BALANCE ("\\s*show\\s+balance\\s*"),
    SHOW_RESTAURANT ("\\s*show\\s+restaurant(\\s+-t\\s+(?<type>\\S+))?\\s*"),
    SHOW_RESTAURANT_OPTION ("\\s+-t\\s+(?<type>\\S+)"),
    SHOW_ORDER_HISTORY ("\\s*show\\s+history\\s*"),
    SHOW_RESTAURANT_COMMENTS ("\\s*show\\s+comments\\s+(?<restaurantName>\\S+)\\s*"),
    SHOW_MENU ("\\s*show\\s+menu\\s+(?<restaurantName>\\S+)(\\s+-c\\s+(?<category>\\S+))?\\s*"),
    SHOW_MENU_OPTION ("\\s+-c\\s+(?<category>\\S+)"),
    CHANGE_LOCATION ("\\s*set\\s+location\\s+(?<location>-?\\d+)\\s*"),
    ADD_TO_CART ("\\s*add\\s+to\\s+cart\\s+(?<restaurantName>\\S+)\\s+(?<foodName>\\S+)(\\s+-n\\s+(?<number>-?\\d+))?\\s*"),
    NUMBER_OPTION ("\\s+-n\\s+(?<number>-?\\d+)"),
    REMOVE_FROM_CART ("\\s*remove\\s+from\\s+cart\\s+(?<restaurantName>\\S+)\\s+(?<foodName>\\S+)(\\s+-n\\s+(?<number>-?\\d+))?\\s*"),
    SHOW_CART ("\\s*show\\s+cart\\s*"),
    SHOW_TIME ("\\s*show\\s+time\\s*"),
    SHOW_DELIVERY ("\\s*show\\s+delivery\\s*"),
    COLLECTED ("\\s*food\\s+collected"),
    ADD_COMMENT ("\\s*add\\s+comment\\s+to\\s+(?<restaurantName>\\S+)\\s*(?<message>(\\S+\\s*)*)"),
    ADD_RATING ("\\s*add\\s+rate\\s+(?<rate>\\S+)\\s+to\\s+(?<orderNumber>\\S+)\\s*"),
    SHOW_DISCOUNTS ("\\s*show\\s+discounts\\s*"),
    PURCHASE_CART ("\\s*purchase\\s+cart(\\s+-d\\s+(?<discountCode>\\S+))?\\s*"),
    PURCHASE_CART_OPTION ("\\s+-d\\s+(?<discountCode>\\S+)");

    private String string;

    CustomerMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(CustomerMenuEnums customerMenuEnums) {
        return customerMenuEnums.string;
    }

    public static Matcher getMatcher(String input, CustomerMenuEnums customerMenuEnums) {
        Matcher matcher = Pattern.compile(customerMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}