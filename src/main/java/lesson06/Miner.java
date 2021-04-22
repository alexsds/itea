package lesson06;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Miner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Miner.class.getName());

    private final String name;
    private int totalGold;

    private final Capacity capacity;
    private final GoldMine mine;

    private final Thread thread;

    public Miner(String name, Capacity capacity, GoldMine mine) {
        this.name = name;
        this.capacity = capacity;
        this.mine = mine;
        thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public String getName() {
        return name;
    }

    public int getTotalGold() {
        return totalGold;
    }

    @Override
    public void run() {
        LOGGER.info(String.format("Miner %s started", name));
        int gold;
        while (mine.hasGold()) {
            try {
                TimeUnit.SECONDS.sleep(capacity.getSeconds());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            gold = mine.mine(capacity.getQuantity());
            totalGold += gold;
        }
        LOGGER.info(String.format("Miner %s finished with totalGold: %s\n", name, totalGold));
    }
}
