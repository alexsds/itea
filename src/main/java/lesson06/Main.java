package lesson06;

public class Main {
    public static void main(String[] args) {
        int initialGold = 1000;
        int initialMiners = 5;
        Capacity barracksCapacity = new Capacity(1, 10);
        Capacity minerCapacity = new Capacity(3, 1);
        int pollingTime = 1;

        GameSettings settings = new GameSettings(initialGold, initialMiners, barracksCapacity, minerCapacity, pollingTime);
        GoldMinerGame game = new GoldMinerGame(settings);
        settings.show();
        game.start();
    }
}
