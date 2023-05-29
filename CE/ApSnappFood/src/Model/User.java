package Model;

public class User {
    private String username;
    private String password;
    private int balance;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        balance = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void changeBalance(int amount) {
        balance += amount;
    }
}
