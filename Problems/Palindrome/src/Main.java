import java.util.Scanner;

/*
Problem
Write a program that reads a string and checks whether it is a palindrome, i.e. it reads the same both left-to-right and right-to-left.
The program must output “yes” if the string is a palindrome and “no” otherwise.
Sample Input:
kayak
Sample Output:
yes
 */
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        StringBuilder builder = new StringBuilder(input);
        String reversed = builder.reverse().toString();
        if (reversed.equalsIgnoreCase(input)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}