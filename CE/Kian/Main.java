import model.user.App;
import view.LoginMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine().trim();
        String password = scanner.nextLine().trim();
        App.setSnappfoodAdmin(username, password);
        App.addUser(App.getSnappfoodAdmin());
        new LoginMenu().run(scanner);
    }
}