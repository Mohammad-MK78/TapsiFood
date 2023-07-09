package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RestaurantMenuEnums {
    LOGOUT ("\\s*logout\\s*"),

    SHOW_CURRENT_MENU ("\\s*show\\s+current\\s+menu\\s*"),
    ADD_FOOD ("\\s*add\\s+food\\s+(?<name>\\S+)\\s+(?<category>\\S+)\\s+(?<price>-?\\d+)\\s+(?<cost>-?\\d+)\\s*"),
    VALID_FOOD_NAME ("[a-z\\-]+"),
    SHOW_BALANCE ("\\s*show\\s+balance\\s*"),
    CHANGE_TYPE ("\\s*change\\s+type\\s+(?<type>\\S+)\\s*"),
    SHOW_COMMENTS ("\\s*show\\s+comments\\s*"),
    REPLY_TO_COMMENT ("\\s*reply\\s+to\\s+comment\\s+(?<commentNum>\\S+)\\s*(?<comment>)"),
    REMOVE_FOOD ("\\s*remove\\s+food\\s+(?<name>\\S+)\\s*");

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
