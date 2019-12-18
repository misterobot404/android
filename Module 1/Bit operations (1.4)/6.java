import java.util.Scanner;

// Дано натуральное число N. Определите является ли число N точной степенью числа 2.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int value = in.nextInt();

        int x = ( value & ( value - 1 ) );

        System.out.printf("%s", x == 0 ? "YES" : "NO");

        in.close();
    }
}