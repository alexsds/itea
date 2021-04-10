package lesson03;

public enum Action {
    CREATE,
    SAVE,
    LOAD,
    EXIT,
    ;

    public static Action fromInteger(int value) {
        switch(value) {
            case 1:
                return CREATE;
            case 2:
                return SAVE;
            case 3:
                return LOAD;
            case 4:
                return EXIT;
        }
        return null;
    }
}
