package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class TaskManager {

    public static final String FILE_NAME = "storage/tasks.csv";

    public static void main(String[] args) {
        //System.out.println(ConsoleColors.BLUE + "Hello, World !!!");
        readTable(FILE_NAME);
        menuOptions();

    }

    public static void menuOptions() {

        String[] menuOptions = {"add", "remove", "list", "exit"};
        System.out.println(pl.coderslab.ConsoleColors.BLUE + "Please select an option:");
        for (String s : menuOptions) {
            System.out.println(pl.coderslab.ConsoleColors.RESET + s);
        }

    }
    //public static String m

    public static String menuChoice() {
//        Scanner scanner = new Scanner(System.in);
//        String choice = "";
//        choice = scanner.nextLine();

//        switch (choice) {
//            case "add":
//                addTask();
//                break;
//            case "remove":
//                removeTask();
//                break;
//            case "list":
//                listTask();
//                break;
//            case "exit":
//                exit();
//                break;

//// other options
//            default:
//                System.out.println("Please select a correct option.");
//        }
        return "a";
    }

    public static String[][] readTable(String fileName) {
        Path path = Paths.get(FILE_NAME);
        File file = new File(FILE_NAME);

        try (Scanner scanner = new Scanner(file)) {

            long rowCounterLong = Files.lines(path).count();
            int rowCounter = (int) rowCounterLong;

            String[][] task = new String[rowCounter][3];

            int i = 0;
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                String[] split = text.split(",");

                for (int j = 0; j < split.length; j++) {
                    //System.out.print(split[j]);
                    task[i][j] = split[j];

                }
                i++;
            }

//            System.out.println("[0][0]: " + task[0][0]);
//            System.out.println("[1][1]: " + task[1][1]);
//            System.out.println("[2][3]: " + task[2][2]);
            return task;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ez) {
            ez.printStackTrace();

        } finally {
            return null;
        }

    }

}


