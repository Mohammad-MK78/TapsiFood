package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CustomerMenuCommands {
    LOGOUT("logout"),
    SHOW_RESTAURANT("\\s*show\\s+restaurant(\\s+-t\\s+(?<type>\\S+))?\\s*"),
    SHOW_MENU("\\s*show\\s+menu\\s+(?<name>\\S+)(\\s+-c\\s+(?<category>\\S+))?\\s*"),
    ADD_TO_CART("\\s*add\\s+to\\s+cart\\s+(?<restaurantName>\\S+)\\s+(?<foodName>\\S+)(\\s+-n\\s+(?<number>\\S+))?\\s*"),
    REMOVE_FROM_CART("\\s*remove\\s+from\\s+cart\\s+(?<restaurantName>\\S+)\\s+(?<foodName>\\S+)(\\s+-n\\s+(?<number>\\S+))?\\s*"),
    SHOW_CART("\\s*show\\s+cart\\s*"),
    SHOW_DISCOUNTS("\\s*show\\s+discounts\\s*"),
    PURCHASE_CART("\\s*purchase\\s+cart(\\s+-d\\s+(?<code>\\S+))?\\s*");


    public String regex;

    CustomerMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, CustomerMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
