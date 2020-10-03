package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] data;
        double total = 0.0;

        StringBuilder output = new StringBuilder();
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            data = input.split("\\$");

            if (!input.isBlank()) {
                total += Float.parseFloat(data[1]);
                output.append(input).append("\n");
            }
        }
        System.out.println(output.toString());
        System.out.printf("Total: $%f", total);
    }
}
