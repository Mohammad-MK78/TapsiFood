package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RestaurantAdminMenuEnums {
    LOGOUT ("\\s*logout\\s*"),
    ADD_RESTAURANT ("\\s*add\\s+restaurant\\s+(?<name>\\S+)\\s+(?<password>\\S+)\\s+(?<type>\\S+)\\s+(?<location>-?\\d+)\\s*"),
    VALID_USERNAME ("[\\w_]*[a-zA-Z][\\w_]*"),
    VALID_PASSWORD ("[\\w_]+"),
    VALID_TYPE ("[a-z\\-]+"),
    REMOVE_RESTAURANT ("\\s*remove\\s+restaurant\\s+(?<name>\\S+)\\s*"),

    SHOW_CURRENT_MENU ("\\s*show\\s+current\\s+menu\\s*"),
    CHARGE_ACCOUNT ("\\s*charge\\s+account\\s+(?<amount>-?\\d+)\\s*"),
    SHOW_BALANCE ("\\s*show\\s+balance\\s*"),
    ADD_FOOD ("\\s*add\\s+food\\s+(?<name>\\S+)\\s+(?<category>\\S+)\\s+(?<price>-?\\d+)\\s+(?<cost>-?\\d+)\\s*"),
    VALID_FOOD_NAME ("[a-z\\-]+"),
    SHOW_COMMENTS ("\\s*show\\s+comments\\s*"),
    CHANGE_TYPE ("\\s*change\\s+type\\s+(?<type>\\S+)\\s*"),
    REPLY_TO_COMMENT ("\\s*reply\\s+to\\s+comment\\s+(?<commentNum>\\S+)\\s*(?<comment>)"),
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
