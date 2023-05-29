package model.user;

public class User {
    private int balance;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public void addBalance(int add){
        balance += add;
    }

    public void reduceBalance(int reduce){
        balance -= reduce;
    }

    public boolean isPasswordCorrect(String password){
        return password.equals(this.password);
    }

    public void setPassword(String password){
        this.password = password;
    }
}
