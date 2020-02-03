// Напишите программу, которая в заданном целом числе A устанавливает значение бита под номером i равным 1. Биты нумеруются с нуля, начиная от младших.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int num = in.nextInt();
        int bit = in.nextInt();

        num |= (1 << bit);

        System.out.printf("%d", num);

        in.close();
    }
}