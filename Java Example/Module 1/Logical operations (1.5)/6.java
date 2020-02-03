import java.util.Scanner;

// Даны 3 целых числа. Требуется определить, есть ли среди этих чисел хотя бы два четных.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int value1 = in.nextInt();
        int value2 = in.nextInt();
        int value3 = in.nextInt();

        int quantity = 0;

        if (value1 % 2 == 0) quantity++;
        if (value2 % 2 == 0) quantity++;
        if (value3 % 2 == 0) quantity++;

        System.out.printf("%s", quantity >= 2 ? "true": "false");

        in.close();
    }
}