package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class TaskManager {

    static String[][] newTab;

    public static final String FILE_NAME = "storage/tasks.csv";

    public static void main(String[] args) {

        newTab = readTable(FILE_NAME);
        menuOptions();
        menuChoice();

    }

    public static void menuOptions() {

        String[] menuOptions = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for (String s : menuOptions) {
            System.out.println(ConsoleColors.RESET + s);
        }

    }

    public static void menuChoice() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (scanner.hasNextLine()) {
            choice = scanner.nextLine();
            switch (choice) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask();
                    break;
                case "list":
                    listTask();
                    break;
                case "exit":
                    listQuit();
                    break;

                default:
                    System.out.println("Please select a correct option.");

            }

        }
    }

    public static void addTask() {
        newTab = Arrays.copyOf(newTab, newTab.length + 1);
        newTab[newTab.length - 1] = new String[3];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String tmp = scanner.nextLine();
        newTab[newTab.length - 1][0] = tmp;
        System.out.println("Please add task due date");
        tmp = scanner.nextLine();
        newTab[newTab.length - 1][1] = tmp;
        System.out.println("Is your task extra special: (true/false)");
        String choice = "";
        while (!choice.equals("true") && !choice.equals("false")) {

            choice = scanner.nextLine();
            System.out.println("Type true/false");

        }
        if ("true".equals(choice)) {
            choice = "true";
        } else if ("false".equals(choice)) {
            choice = "false";
        }


        tmp = choice;

        newTab[newTab.length - 1][2] = tmp;
        menuOptions();

    }

    public static void listTask() {

        for (int i = 1; i <= newTab.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < newTab[i - 1].length; j++) {
                System.out.print(newTab[i - 1][j] + " ");
            }
            System.out.println("");


        }
        menuOptions();
    }


    public static void listQuit() {
        Path path1 = Paths.get(FILE_NAME);
        List<String> outList = new ArrayList<>();
        String tmp = "";
        for (int i = 0; i < newTab.length; i++) {
            for (int j = 0; j < newTab[i].length; j++) {
                tmp = String.join(", ", newTab[i]);

            }

            outList.add(tmp);
        }

        try {
            Files.write(path1, outList);
        } catch (IOException ex) {
            System.out.println("Nie można zapisać pliku.");
        }
        System.out.println(ConsoleColors.RED + "Have a good day :)");
        System.exit(0);


    }

    public static void removeTask() {
        System.out.println("Choose task to remove (listed number)");
        Scanner scanner = new Scanner(System.in);
        int number = 0;

        try {
            number = scanner.nextInt();
            while ((number <= 0) || (number > newTab.length)) {
                System.out.println("Incorrect number - no such task. Try again:");
                number = scanner.nextInt();
            }
            newTab = ArrayUtils.remove(newTab, (number - 1));
            System.out.println("You chose to remove task " + (number));
            System.out.println("Your current task list is following:");
            listTask();
        } catch (InputMismatchException e) {
            System.out.println("Not an integer number");
            removeTask();
        }

    }


    public static String[][] readTable(String fileName) {
        Path location = Paths.get(FILE_NAME);
        String[][] tasks = null;
        if (!Files.exists(location)) {
            System.out.println("File not exist.");
            System.exit(0);
        }
        try {
            Scanner scanner = new Scanner(location);

            long rowCounterLong = Files.lines(location).count(); //zliczam liczbę wierszy w pliku, do stworzenia nowej tablicy
            int rowCounter = (int) rowCounterLong;

            tasks = new String[rowCounter][3];

            int i = 0;
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                String[] split = text.split(",");

                for (int j = 0; j < split.length; j++) {

                    tasks[i][j] = split[j];

                }
                i++;
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("Błąd pliku");
        }
        return tasks;
    }

}













