package Model;

public class Discount {
    private String code;
    private int discountAmount;
    private Customer discountUser;

    public Discount(Customer discountUser, int discountAmount, String code) {
        this.discountUser = discountUser;
        this.discountAmount = discountAmount;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public Customer getDiscountUser() {
        return discountUser;
    }
}
