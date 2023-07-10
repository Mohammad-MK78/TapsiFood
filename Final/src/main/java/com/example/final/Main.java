import View.LoginMenu;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        LoginMenu.run(scanner);
    }
}
