package view.enums.commands;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SnappfoodAdminMenuCommands {
    LOGOUT("\\s*logout\\s*"),
    ADD_RESTAURANT("\\s*add\\s+restaurant\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s+(?<type>\\S+)\\s*"),
    SHOW_RESTAURANT("\\s*show\\s+restaurant(\\s+-t\\s+(?<type>\\S+))?\\s*"),
    VALID_TYPE("[a-z-]+"),
    REMOVE_RESTAURANT("\\s*remove\\s+restaurant\\s+(?<username>\\S+)\\s*"),
    SET_DISCOUNT("\\s*set\\s+discount\\s+(?<username>\\S+)\\s+(?<amount>-?\\d+)\\s+(?<code>\\S+)\\s*"),
    VALID_DISCOUNT_CODE("[a-zA-Z0-9]+"),
    SHOW_DISCOUNTS("\\s*show\\s+discounts\\s*");

    public String regex;

    SnappfoodAdminMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, SnappfoodAdminMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
