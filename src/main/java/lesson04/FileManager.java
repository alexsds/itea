package lesson04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class FileManager {
    public void run() {
        String originalInput;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to FileManager");
        help();

        while (true) {
            originalInput = scanner.nextLine();
            String input = originalInput.toUpperCase().trim();
            String inputCommand = input.split(" ")[0];
            if (!Command.contains(inputCommand)) {
                wrongCommandErrorMessage();
                continue;
            }

            Command action = Command.valueOf(inputCommand);
            switch (Objects.requireNonNull(action)) {
                case TYPE:
                    type(originalInput);
                    break;
                case COPY:
                    copy(originalInput);
                    break;
                case HELP:
                    help();
                    break;
                case EXIT:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    wrongCommandErrorMessage();
                    break;
            }
        }
    }

    private void help() {
        System.out.println("Commands:");
        System.out.println("type [<drive>:][<path>]<filename>   View file content");
        System.out.println("copy <source> <destination>         Copy file");
        System.out.println("exit                                Exit program");
        System.out.println("help                                Display help information");
    }

    private void type(String input) {
        if (input.split(" ").length != 2) {
            System.out.println("type: illegal option");
            System.out.println("usage: type [<drive>:][<path>]<filename>");
            return;
        }

        String pathname = input.split(" ")[1];
        try {
            File file = new File(pathname);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            System.out.println(sb);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Can't read file");
        }
    }

    private void copy(String input) {
        if (input.split(" ").length != 3) {
            System.out.println("copy: illegal option");
            System.out.println("usage: copy <source> <destination>");
            return;
        }

        String source = input.split(" ")[1];
        String destination = input.split(" ")[2];
        File original = new File(source);
        Path copied = Paths.get(destination);
        Path originalPath = original.toPath();
        try {
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied");
        }
        catch (NoSuchFileException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Can't read file");
        }
    }

    private void wrongCommandErrorMessage() {
        System.out.println("Wrong command, available: " + Arrays.toString(Command.values()).toLowerCase());
    }
}
