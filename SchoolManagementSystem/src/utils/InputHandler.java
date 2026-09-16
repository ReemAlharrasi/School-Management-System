package utils;

import java.util.Scanner;

// Reads input from the keyboard. Every read method checks the value
// through HelperUtils before returning it. Overloaded read methods
// let us ask for text, numbers, ranges, yes/no, or a word from a set.
public class InputHandler {

    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    // ---------- tiny private checks (no generics) ----------

    private boolean isWholeNumber(String line) {
        if (HelperUtils.isEmpty(line)) {
            return false;
        }
        try {
            Integer.parseInt(line.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDecimalNumber(String line) {
        if (HelperUtils.isEmpty(line)) {
            return false;
        }
        try {
            Double.parseDouble(line.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // read some non-empty text
    public String readText(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String line = scanner.nextLine();
            if (HelperUtils.isValidText(line)) {
                return line.trim();
            }
            System.out.println("Please type something (it cannot be empty).");
        }
    }

    // read any whole number
    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String line = scanner.nextLine();
            if (isWholeNumber(line)) {
                return Integer.parseInt(line.trim());
            }
            System.out.println("Please type a whole number.");
        }
    }

    // read a whole number within min..max, repeating until it is valid (overload)
    public int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt + " (" + min + "-" + max + ")");
            if (HelperUtils.inRange(value, min, max)) {
                return value;
            }
            System.out.println("Number must be between " + min + " and " + max + ".");
        }
    }

    // read a decimal number
    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String line = scanner.nextLine();
            if (isDecimalNumber(line)) {
                return Double.parseDouble(line.trim());
            }
            System.out.println("Please type a number (decimals allowed).");
        }
    }

    // read a single letter, used for grades like A, B, C
    public char readChar(String prompt) {
        while (true) {
            String line = readText(prompt);
            if (line.length() == 1) {
                return line.charAt(0);
            }
            System.out.println("Please type exactly one character.");
        }
    }

    // read a yes/no answer, return true for yes
    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.equals("yes") || line.equals("y")) {
                return true;
            }
            if (line.equals("no") || line.equals("n")) {
                return false;
            }
            System.out.println("Please answer yes or no.");
        }
    }

    // read a value that must be one of the allowed words
    public String readOneOf(String prompt, String[] allowed) {
        while (true) {
            String line = readText(prompt);
            if (HelperUtils.isOneOf(line, allowed)) {
                return line;
            }
            System.out.print("Allowed values are: ");
            for (int i = 0; i < allowed.length; i++) {
                System.out.print(allowed[i]);
                if (i < allowed.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
