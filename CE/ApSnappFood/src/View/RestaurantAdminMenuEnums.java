package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RestaurantAdminMenuEnums {
    LOGOUT ("\\s*logout\\s*"),
    SHOW_CURRENT_MENU ("\\s*show\\s+current\\s+menu\\s*"),
    CHARGE_ACCOUNT ("\\s*charge\\s+account\\s+(?<amount>-?\\d+)\\s*"),
    SHOW_BALANCE ("\\s*show\\s+balance\\s*"),
    ADD_FOOD ("\\s*add\\s+food\\s+(?<name>\\S+)\\s+(?<category>\\S+)\\s+(?<price>-?\\d+)\\s+(?<cost>-?\\d+)\\s*"),
    VALID_FOOD_NAME ("[a-z\\-]+"),
    REMOVE_FOOD ("\\s*remove\\s+food\\s+(?<name>\\S+)\\s*");

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
