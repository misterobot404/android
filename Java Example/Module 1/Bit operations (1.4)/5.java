package com.company;
import java.util.Scanner;

// Напишите программу, обнуляющие все биты целого числа А, кроме i младших бит.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int value = in.nextInt();
        int bit = in.nextInt();

        for (int temp_value = value, i = 0; temp_value > 0; temp_value >>= 1, i++) {
            if (i >= bit)
                value &= ~(1 << i);
        }

        System.out.printf("%d", value);

        in.close();
    }
}
