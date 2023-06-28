package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuEnums {
    SNAPP_FOOD_ADMIN_INPUT ("\\s*(?<input>\\S+)\\s*"),
    REGISTER ("\\s*register\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    VALID_USERNAME ("[\\w_]*[a-zA-Z][\\w_]*"),
    VALID_PASSWORD ("[\\w_]+"),
    LOGIN ("\\s*login\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    DELIVERY_REGISTER ("\\s*delivery\\s+register\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s+(?<location>\\S+)\\s*"),
    CHANGE_PASSWORD ("\\s*change\\s+password\\s+(?<username>\\S+)\\s+(?<oldPassword>\\S+)\\s+(?<newPassword>\\S+)\\s*"),
    REMOVE_ACCOUNT ("\\s*remove\\s+account\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    EXIT ("\\s*exit\\s*"),
    SHOW_CURRENT_MENU ("\\s*show\\s+current\\s+menu\\s*");

    private String regex;
    LoginMenuEnums(String string) {
        this.regex = string;
    }

    public static String getRegex(LoginMenuEnums loginMenuEnums) {
        return loginMenuEnums.regex;
    }

    public static Matcher getMatcher(String input, LoginMenuEnums loginMenuEnums) {
        Matcher matcher = Pattern.compile(loginMenuEnums.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
