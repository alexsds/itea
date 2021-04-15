package lesson04;

public enum Command {
    TYPE,
    COPY,
    HELP,
    EXIT,
    ;

    public static boolean contains(String test) {
        for (Command c : Command.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
}
