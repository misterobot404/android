// Напишите программу, которая инвертирует i-ый бит в заданном числе A. Биты нумеруются с 0, начиная с младших.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int value = in.nextInt();
        int bit = in.nextInt();

        System.out.printf("%d", value ^= (1 << bit));

        in.close();
    }
}