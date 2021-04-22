package lesson06;

public class GoldMinerGame {
    private final GameSettings settings;

    public GoldMinerGame(GameSettings settings) {
        this.settings = settings;
    }

    public void start() {
        GoldMine mine = new GoldMine(settings.getInitialGold());
        Barracks barracks = new Barracks(settings.getBarracksCapacity(), settings.getMinerCapacity(), mine);

        for (int i = 1; i <= settings.getInitialMiners(); i++) {
            barracks.create();
        }

        barracks.getMiners().forEach(Miner::start);
        barracks.start();

        ProgressLogger progressLogger = new ProgressLogger(mine, barracks, settings.getPollingTime());
        progressLogger.start();
    }
}
