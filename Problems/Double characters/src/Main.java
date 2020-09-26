import java.util.Scanner;
/*
Double characters
Write a program that reads a string and then output another string with doubled characters.
Sample Input:
The
Sample Output:
TThhee
 */
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        StringBuilder finale = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            finale.append(input.charAt(i)).append(input.charAt(i));
        }
        System.out.println(finale);
    }
}