package lesson06;

public class GameSettings {
    private final static String TEMPLATE = "Settings:\n" +
            "GoldMine has %s gold.\n" +
            "%s miners at the start.\n" +
            "Miner capacity %s gold per %s sec.\n" +
            "Barracks capacity %s miner(s) per %s sec.\n" +
            "Progress logger polling time %s sec.\n";

    private final int initialGold;
    private final int initialMiners;
    private final Capacity barracksCapacity;
    private final Capacity minerCapacity;
    private final int pollingTime;

    public GameSettings(int initialGold, int initialMiners, Capacity barracksCapacity, Capacity minerCapacity, int pollingTime) {
        this.initialGold = initialGold;
        this.initialMiners = initialMiners;
        this.barracksCapacity = barracksCapacity;
        this.minerCapacity = minerCapacity;
        this.pollingTime = pollingTime;
    }

    public int getInitialGold() {
        return initialGold;
    }

    public int getInitialMiners() {
        return initialMiners;
    }

    public Capacity getBarracksCapacity() {
        return barracksCapacity;
    }

    public Capacity getMinerCapacity() {
        return minerCapacity;
    }

    public int getPollingTime() {
        return pollingTime;
    }

    public void show() {
        String message = String.format(
                TEMPLATE,
                initialGold,
                initialMiners,
                minerCapacity.getQuantity(),
                minerCapacity.getSeconds(),
                barracksCapacity.getQuantity(),
                barracksCapacity.getSeconds(),
                pollingTime
        );

        System.out.println(message);
    }
}
