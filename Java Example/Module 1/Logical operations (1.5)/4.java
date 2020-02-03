import java.util.Scanner;

// Дано целое число. Требуется определить, является ли данное число трехзначным положительным числом, кратным пяти..

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int value = in.nextInt();
        int length = 0;

        for (int temp_value = value; temp_value!=0; temp_value /= 10, length ++);

        System.out.printf("%s", (length == 3) && (value % 5 == 0) && (value > 0) ? "true" : "false");

        in.close();
    }
}