package lesson06;

public class Capacity {
    private final int quantity;
    private final int seconds;

    public Capacity(int quantity, int seconds) {
        this.quantity = quantity;
        this.seconds = seconds;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSeconds() {
        return seconds;
    }
}
