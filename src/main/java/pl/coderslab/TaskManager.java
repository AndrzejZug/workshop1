package pl.coderslab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class TaskManager {

    static String[][] newTab;

    public static final String FILE_NAME = "storage/tasks.csv";

    public static void main(String[] args) {
        //System.out.println(ConsoleColors.BLUE + "Hello, World !!!");

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
    //public static String m

    public static void menuChoice() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (scanner.hasNextLine()) {
            choice = scanner.nextLine();
            {
                switch (choice) {
                    case "add":
                        addTask();
                        break;
                    case "remove":
                        System.out.println("removeTask()");
                        break;
                    case "list":
                        listTask();
                        break;
                    case "exit":
                        listQuit();
                        break;

// other options
                    default:
                        System.out.println("Please select a correct option.");

                }

            }
        }
    }

    public static void addTask() {
//        array = Arrays.copyOf(array, len + 1); // copying the array
//        array[len] = new String[2]; // creating and assigning string array to new row else it will be null
//        array[len][0] = str
        newTab = Arrays.copyOf(newTab, newTab.length + 1);
        newTab[newTab.length - 1] = new String[3];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String tmp = scanner.nextLine();
        newTab[newTab.length - 1][0] = tmp;
        System.out.println("Please add task due date");
        tmp = scanner.nextLine();
        newTab[newTab.length - 1][1] = tmp;
        System.out.println("Is your task extra special: true/false");
        tmp = scanner.nextLine();
        newTab[newTab.length - 1][2] = tmp;
//        System.out.print(newTab[newTab.length - 1][0] + " ");
//        System.out.print(newTab[newTab.length - 1][1] + " ");
//        System.out.println(newTab[newTab.length - 1][2] + " ");
//        System.out.print(newTab[newTab.length - 2][0] + " ");
//        System.out.print(newTab[newTab.length - 2][1] + " ");
//        System.out.println(newTab[newTab.length - 2][2] + " ");
        menuOptions();

    }

    public static void listTask() {

        for (int i = 0; i < newTab.length; i++) {
            for (int j = 0; j < newTab[i].length; j++) {
                System.out.print(" " + newTab[i][j]);
            }
            System.out.println("");


        }
        menuOptions();
    }

    public static void listQuit() {

        System.out.println(ConsoleColors.RED + "Have a good day :)");
        System.exit(0);

    }


    public static String[][] readTable(String fileName) {
        File file = new File(FILE_NAME);
        Path location = Paths.get(FILE_NAME);
        String[][] tasks = null;
        if (!Files.exists(location)) {
            System.out.println("File not exist.");
            System.exit(0);
        }
        try {
            Scanner scanner = new Scanner(file);

            long rowCounterLong = Files.lines(location).count();
            int rowCounter = (int) rowCounterLong;

            tasks = new String[rowCounter][3];

            int i = 0;
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                String[] split = text.split(",");

                for (int j = 0; j < split.length; j++) {
                    //System.out.print(split[j]);
                    tasks[i][j] = split[j];

                }
                i++;
            }
        scanner.close();

        } catch (IOException e) {
            System.out.println("Bład pliku");
        }
        return tasks;
    }

}













