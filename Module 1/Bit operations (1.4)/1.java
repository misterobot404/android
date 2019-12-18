// Напишите программу, которая обнуляет заданное количество последних бит числа.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int num = in.nextInt();
        int shift = in.nextInt();

        System.out.printf("%d", num & (-1 << shift));

        in.close();
    }
}