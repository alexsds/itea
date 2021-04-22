package lesson06;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ProgressLogger implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(ProgressLogger.class.getName());

    private final GoldMine mine;
    private final Barracks barracks;
    private final int pollingTime;

    private final Thread thread;

    public ProgressLogger(GoldMine mine, Barracks barracks, int pollingTime) {
        this.mine = mine;
        this.barracks = barracks;
        this.pollingTime = pollingTime;
        thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public void log() {
        System.out.printf("Golds left in mine: %s\n", mine.getGold());
        barracks.getMiners().forEach((miner) -> System.out.printf("Miner %s total gold: %s\n", miner.getName(), miner.getTotalGold()));
    }

    @Override
    public void run() {
        LOGGER.info("ProgressLogger started");
        while (mine.hasGold()) {
            try {
                TimeUnit.SECONDS.sleep(pollingTime);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            log();
        }
        LOGGER.info("ProgressLogger finished");
    }
}
