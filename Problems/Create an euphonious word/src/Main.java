import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here;
        String VOWELS = "aeiouy";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        //help from https://stackoverflow.com/questions/63671303/create-a-euphonious-word
        int count = 0;
        int currentTypeCount = 0;
        boolean lastTypeVowel = true;
        char[] word = input.toCharArray();
        for (char c : word) {
            if ((VOWELS.indexOf(c) != -1) == lastTypeVowel) {
                if (++currentTypeCount == 3) {
                    count++;
                    currentTypeCount = 1;
                }
            } else {
                lastTypeVowel = !lastTypeVowel;
                currentTypeCount = 1;
            }
        }
        System.out.println(count);
    }

}