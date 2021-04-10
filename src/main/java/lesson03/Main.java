package lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

class Main {
    private static final String LOCALE_SHORT_NAME_RU = "ru";
    private static final String LOCALE_SHORT_NAME_EN = "en";
    private static final Map<String, Locale> LOCALES = new HashMap<String, Locale>() {{
        put(LOCALE_SHORT_NAME_RU, new Locale("ru", "RU"));
        put(LOCALE_SHORT_NAME_EN, new Locale("en", "US"));
    }};
    private static final String PUDGE_FILE_NAME = "pudge.ser";

    private static ResourceBundle resourceBundle;
    private static Pudge pudge;

    public static void main(String[] args) {
        initTranslation(args);
        try {
            run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initTranslation(String[] args) {
        String localeShortName = LOCALE_SHORT_NAME_RU;
        if (args.length != 0) {
            localeShortName = args[0];
        }
        Locale locale = getLocale(localeShortName);
        resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    private static void run() throws IOException {
        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            choice = getChoice(scanner);
            Action action = Action.fromInteger(choice);
            switch (Objects.requireNonNull(action)) {
                case CREATE:
                    crate();
                    System.out.println("pudge: " + pudge);
                    break;
                case SAVE:
                    save();
                    break;
                case LOAD:
                    load();
                    System.out.println("pudge: " + pudge);
                    break;
                case EXIT:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("error");
                    scanner.close();
                    System.exit(1);
                    break;
            }
        }
    }

    private static void crate() {
        pudge = new Pudge(25, 14, 16, Utils.randomString(10));
    }

    private static void save() throws IOException {
        if (pudge == null) {
            System.out.println(resourceBundle.getString("error_no_data"));
            return;
        }

        try (FileOutputStream fileOut = new FileOutputStream(PUDGE_FILE_NAME);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(pudge);
        }
        catch (FileNotFoundException e) {
            System.out.println(resourceBundle.getString("error_no_data"));
        }
    }

    private static void load() throws IOException {
        try (FileInputStream fileIn = new FileInputStream(PUDGE_FILE_NAME);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            pudge = (Pudge) in.readObject();
        }
        catch (FileNotFoundException e) {
            System.out.println(resourceBundle.getString("error_no_data"));
        }
        catch (ClassNotFoundException e) {
            System.out.println(resourceBundle.getString("error_invalid_save"));
        }
    }

    private static Locale getLocale(String localeShortName) {
        switch (localeShortName) {
            case LOCALE_SHORT_NAME_EN:
                return LOCALES.get(LOCALE_SHORT_NAME_EN);
            case LOCALE_SHORT_NAME_RU:
            default:
                return LOCALES.get(LOCALE_SHORT_NAME_RU);
        }
    }

    private static int getChoice(Scanner scanner) {
        System.out.println(resourceBundle.getString("actions_title"));
        System.out.println("1 - " + resourceBundle.getString("action_create"));
        System.out.println("2 - " + resourceBundle.getString("action_save"));
        System.out.println("3 - " + resourceBundle.getString("action_load"));
        System.out.println("4 - " + resourceBundle.getString("action_exit"));

        int choice;
        while (!scanner.hasNext("[1-4]")) {
            System.out.println(resourceBundle.getString("error_wrong_option"));
            scanner.next();
        }
        choice = scanner.nextInt();

        return choice;
    }
}
