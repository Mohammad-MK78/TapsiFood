package model;

public class Order {
    private Food food;
    private int count;

    public Order(Food food, int count) {
        this.food = food;
        this.count = count;
    }

    public Food getFood(){
        return food;
    }

    public int getCount(){
        return count;
    }

    public void reduceCount(int reduce){
        count = count - reduce;
    }

    public void addCount(int add){
        count = count + add;
    }
}
