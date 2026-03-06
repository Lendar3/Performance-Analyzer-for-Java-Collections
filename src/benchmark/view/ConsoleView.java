package benchmark.view;

import benchmark.model.CollectionType;
import benchmark.model.DataType;
import benchmark.model.OutputType;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Collection Tester CLI");
        System.out.println("---------------------");
    }

    public DataType chooseDataType() {
        System.out.println();
        System.out.println("Choose Data Type:");
        for (DataType type : DataType.values()) {
            System.out.println(type.getOption() + " " + type.getLabel());
        }
        while (true) {
            int selected = readPositiveInt("Enter selection number: ");
            try {
                return DataType.fromOption(selected);
            } catch (IllegalArgumentException ex) {
                System.out.println("Please choose one of the listed options.");
            }
        }
    }

    public int chooseElementCount() {
        System.out.println();
        System.out.println("Choose number of elements (examples: 100, 500, 1000, 10000):");
        return readPositiveInt("Enter element count: ");
    }

    public int chooseIterations() {
        System.out.println();
        System.out.println("How many iterations per test should be averaged?");
        return readPositiveInt("Enter iterations (recommended 5): ");
    }

    public CollectionType chooseCollectionType() {
        System.out.println();
        System.out.println("Choose Collection Type:");
        for (CollectionType type : CollectionType.values()) {
            System.out.println(type.getOption() + " " + type.getLabel());
        }
        while (true) {
            int selected = readPositiveInt("Enter selection number: ");
            try {
                return CollectionType.fromOption(selected);
            } catch (IllegalArgumentException ex) {
                System.out.println("Please choose one of the listed options.");
            }
        }
    }

    public OutputType chooseOutputType() {
        System.out.println();
        System.out.println("Choose Output Type:");
        for (OutputType type : OutputType.values()) {
            System.out.println(type.getOption() + " " + type.getLabel());
        }
        while (true) {
            int selected = readPositiveInt("Enter selection number: ");
            try {
                return OutputType.fromOption(selected);
            } catch (IllegalArgumentException ex) {
                System.out.println("Please choose one of the listed options.");
            }
        }
    }

    public boolean askRunAgain() {
        while (true) {
            System.out.print("Run another benchmark? (y/n): ");
            String value = scanner.nextLine().trim().toLowerCase();
            if ("y".equals(value) || "yes".equals(value)) {
                return true;
            }
            if ("n".equals(value) || "no".equals(value)) {
                return false;
            }
            System.out.println("Please answer 'y' or 'n'.");
        }
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void close() {
        scanner.close();
    }

    private int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(raw);
                if (value > 0) {
                    return value;
                }
                System.out.println("Please enter a positive number.");
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
