package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuEnums {

    LOGOUT ("\\s*logout\\s*"),
    ENTER_MENU ("\\s*enter\\s+(?<menuName>[\\S\\s]+)"),
    CUSTOMER_MENU ("customer menu\\s*"),
    RESTAURANT_ADMIN_MENU ("restaurant admin menu\\s*"),
    SNAPP_FOOD_ADMIN_MENU ("Snappfood admin menu\\s*"),
    CUSTOMER_MENU_SUCCESSFUL ("enter menu successful: You are in the customer menu!"),
    RESTAURANT_ADMIN_MENU_SUCCESSFUL ("enter menu successful: You are in the restaurant admin menu!"),
    SNAPP_FOOD_ADMIN_MENU_SUCCESSFUL ("enter menu successful: You are in the Snappfood admin menu!"),
    INVALID_MENU_NAME_ERROR ("enter menu failed: invalid menu name"),
    ACCESS_DENIED_ERROR ("enter menu failed: access denied"),
    SHOW_CURRENT_MENU ("\\s*show\\s+current\\s+menu\\s*");


    private String string;

    MainMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(MainMenuEnums mainMenuEnums) {
        return mainMenuEnums.string;
    }

    public static Matcher getMatcher(String input, MainMenuEnums mainMenuEnums) {
        Matcher matcher = Pattern.compile(mainMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
