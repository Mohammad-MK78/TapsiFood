package View;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public enum DeliveryMenuEnums {
    LOGOUT ("\\s*logout\\s*"),
    SHOW_CURRENT_MENU ("\\s*show\\s+current\\s+menu\\s*"),
    SHOW_RESTAURANT ("\\s*show\\s+restaurant\\s*"),
    SHOW_DESTINATION ("\\s*show\\s+destination\\s*"),
    SHOW_LOCATION ("\\s*show\\s+location\\s*"),
    SHOW_TIME ("\\s*show\\s+time\\s*"),
    SHOW_PATH ("\\s*show\\s+path\\s*");

    private String string;

    DeliveryMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(DeliveryMenuEnums deliveryMenuEnums) {
        return deliveryMenuEnums.string;
    }

    public static Matcher getMatcher(String input, DeliveryMenuEnums deliveryMenuEnums) {
        Matcher matcher = Pattern.compile(deliveryMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
