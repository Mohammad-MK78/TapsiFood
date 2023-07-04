package Model;

public class User {
    private String username;
    private String password;
    private int balance, location;

    public User(String username, String password, int location) {
        this.username = username;
        this.password = password;
        this.location = location;
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

    public void setLocation(int location) {
        this.location = location;
    }

    public int getLocation() {
        return location;
    }
}
