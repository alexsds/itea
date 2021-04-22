package lesson06;

public class GoldMine {
    private volatile int gold;

    public GoldMine(int initialGold) {
        gold = initialGold;
    }

    public synchronized int getGold() {
        return gold;
    }

    public synchronized int mine(int quantity) {
        if (gold == 0) {
            return 0;
        }

        int possibleQuantity = Math.min(gold, quantity);
        gold -= possibleQuantity;

        return possibleQuantity;
    }

    public synchronized boolean hasGold() {
        return gold != 0;
    }
}
