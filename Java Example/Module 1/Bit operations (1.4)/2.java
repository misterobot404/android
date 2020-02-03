// Напишите программу, вычисляющую заданную степень числа 2, используя битовые операции.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.printf("%d", 1 << in.nextInt());

        in.close();
    }
}