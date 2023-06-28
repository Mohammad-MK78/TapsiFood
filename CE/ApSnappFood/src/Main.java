import View.LoginMenu;

import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        LoginMenu.run(scanner);
    }
}
