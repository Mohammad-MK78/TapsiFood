package com.example.Final.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SnappFoodAdminMenuEnums {
    LOGOUT ("\\s*logout\\s*"),
    SHOW_CURRENT_MENU ("\\s*show\\s+current\\s+menu\\s*"),
    REMOVE_RESTAURANT_MANAGER ("\\s*remove\\s+restaurant\\s+manager\\s+(?<username>\\S+)\\s*"),
    VALID_USERNAME ("[\\w_]*[a-zA-Z][\\w_]*"),
    VALID_PASSWORD ("[\\w_]+"),
    VALID_TYPE ("[a-z\\-]+"),
    SHOW_RESTAURANT ("\\s*show\\s+restaurant(\\s+-t\\s+(?<type>\\S+))?\\s*"),
    SHOW_RESTAURANT_OPTION ("\\s+-t\\s+(?<type>\\S+)"),
    SHOW_DISCOUNTS ("\\s*show\\s+discounts\\s*");

    private String string;

    SnappFoodAdminMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(SnappFoodAdminMenuEnums snappFoodAdminMenuEnums) {
        return snappFoodAdminMenuEnums.string;
    }

    public static Matcher getMatcher(String input, SnappFoodAdminMenuEnums snappFoodAdminMenuEnums) {
        Matcher matcher = Pattern.compile(snappFoodAdminMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
