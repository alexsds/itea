package lesson03;

public enum Action {
    CREATE,
    SAVE,
    LOAD,
    EXIT,
    ;

    public static Action fromInteger(int value) {
        Action action = null;

        switch(value) {
            case 1:
                action = CREATE;
                break;
            case 2:
                action = SAVE;
                break;
            case 3:
                action = LOAD;
                break;
            case 4:
                action = EXIT;
                break;
        }

        return action;
    }
}
