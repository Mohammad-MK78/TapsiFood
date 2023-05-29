package Controller;

import Model.Customer;
import Model.RestaurantManager;
import Model.SnappFood;
import Model.SnappFoodManager;
import View.MainMenu;
import View.MainMenuEnums;

import java.util.regex.Matcher;

public class MainMenuController {

    public static String enterMenu(Matcher matcher) {
        String menuName = matcher.group("menuName");

        if(MainMenuEnums.getMatcher(menuName, MainMenuEnums.CUSTOMER_MENU) != null)
            return (SnappFood.getCurrentUser() instanceof Customer) ?
                    MainMenuEnums.getString(MainMenuEnums.CUSTOMER_MENU_SUCCESSFUL) :
                    MainMenuEnums.getString(MainMenuEnums.ACCESS_DENIED_ERROR);

        else if(MainMenuEnums.getMatcher(menuName, MainMenuEnums.RESTAURANT_ADMIN_MENU) != null)
            return (SnappFood.getCurrentUser() instanceof RestaurantManager) ?
                    MainMenuEnums.getString(MainMenuEnums.RESTAURANT_ADMIN_MENU_SUCCESSFUL) :
                    MainMenuEnums.getString(MainMenuEnums.ACCESS_DENIED_ERROR);

        else if(MainMenuEnums.getMatcher(menuName, MainMenuEnums.SNAPP_FOOD_ADMIN_MENU) != null)
            return (SnappFood.getCurrentUser() instanceof SnappFoodManager) ?
                    MainMenuEnums.getString(MainMenuEnums.SNAPP_FOOD_ADMIN_MENU_SUCCESSFUL) :
                    MainMenuEnums.getString(MainMenuEnums.ACCESS_DENIED_ERROR);

        else
            return MainMenuEnums.getString(MainMenuEnums.INVALID_MENU_NAME_ERROR);

    }
}
