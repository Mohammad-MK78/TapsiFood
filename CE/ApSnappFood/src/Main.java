import View.LoginMenu;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        LoginMenu.run(scanner);
    }
}
