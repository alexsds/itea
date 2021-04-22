package lesson06;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Barracks implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Barracks.class.getName());
    private final static String MINER_NAME_TEMPLATE = "miner%s";

    private final Capacity capacity;
    private final Capacity minerCapacity;
    private final GoldMine mine;

    private final Set<Miner> miners = new HashSet<>();

    private final Thread thread;

    public Barracks(Capacity capacity, Capacity minerCapacity, GoldMine mine) {
        this.capacity = capacity;
        this.minerCapacity = minerCapacity;
        this.mine = mine;

        thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public Miner create() {
        Miner miner = new Miner(getNewName(), minerCapacity, mine);
        miners.add(miner);

        return miner;
    }

    public Set<Miner> getMiners() {
        return miners;
    }

    private String getNewName() {
        return String.format(MINER_NAME_TEMPLATE, miners.size() + 1);
    }

    @Override
    public void run() {
        LOGGER.info("Barracks started");
        while (mine.hasGold()) {
            try {
                TimeUnit.SECONDS.sleep(capacity.getSeconds());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 1; i <= capacity.getQuantity(); i++) {
                if (mine.hasGold()) {
                    Miner miner = create();
                    miner.start();
                }
            }
        }
        LOGGER.info("Barracks finished");
    }
}
