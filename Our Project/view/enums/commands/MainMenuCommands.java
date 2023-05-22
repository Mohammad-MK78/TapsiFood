package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    LOGOUT("\\s*logout\\s*"),
    SHOW_MENU("\\s*show\\s+current\\s+menu\\s*"),
    ENTER_CUSTOMER_MENU("\\s*enter\\s+customer\\s+menu\\s*"),
    ENTER_RESTAURANT_ADMIN_MENU("\\s*enter\\s+restaurant\\s+admin\\s+menu\\s*"),
    ENTER_SNAPPFOOD_ADMIN_MENU("\\s*enter\\s+Snappfood\\s+admin\\s+menu\\s*");

    public String regex;

    MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MainMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
