package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RestaurantAdminMenuCommands {
    LOGOUT("\\s*logout\\s*"),
    CHARGE_ACCOUNT("\\s*charge\\s+account\\s+(?<amount>-?\\d+)\\s*"),
    SHOW_BALANCE("\\s*show\\s+balance\\s*"),
    ADD_FOOD("\\s*add\\s+food\\s+(?<name>\\S+)\\s+(?<category>\\S+)\\s+(?<price>-?\\d+)\\s+(?<cost>-?\\d+)\\s*"),
    VALID_NAME("[a-z-]+"),
    SHOW_MENU("\\s*show\\s+current\\s+menu\\s*"),
    REMOVE_FOOD("\\s*remove\\s+food\\s+(?<name>\\S+)\\s*");

    public String regex;

    RestaurantAdminMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, RestaurantAdminMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
