/*
For a given array, using Bubble sorting, count the number of swaps (exchanges of two numbers) you need to do to fully sort the array in the ascending order. You need to output the number of swaps.
Sample Input:
2 1 3 4 5
Sample Output:
1
Sample Input:
8 3 4 6 5 2 1
Sample Output:
16
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.asList(scanner.nextLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
        System.out.println(numberOfSwaps(array));
    }

    public static int numberOfSwaps(int[] array) {
        int swaps = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swaps++;
                }
            }
        }

        return swaps;
    }
}
