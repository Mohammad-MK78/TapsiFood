package Model;

import java.sql.*;

public class User {
    private String username, password, position, security_question;
    private int credit, location;

    public User(String username, String password, int location,String position, String security_question) {
        this.username = username;
        this.password = password;
        this.location = location;
        this.position = position;
        this.security_question = security_question;
        credit = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPosition() {
        return position;
    }

    public String getSecurity_question() {
        return security_question;
    }

    public void setPassword(String password) throws ClassNotFoundException, SQLException {
        String username = getUsername();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Mohammad78");
        Statement statement = connection.createStatement();
        String sqlRegAdmin = "SELECT * FROM tapsifood.accounts where username='" + username + "'";
        ResultSet usernameCheck = statement.executeQuery(sqlRegAdmin);
        if (usernameCheck.next()) {
            String change = "UPDATE tapsifood.accounts SET password='" + password + "' WHERE username='" + username + "'";
            statement.executeUpdate(change);
        }
        this.password = password;
    }

    public int getCredit() {
        return credit;
    }

    public void changeBalance(int amount) {
        credit += amount;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getLocation() {
        return location;
    }
}
