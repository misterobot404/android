package com.company;
import java.util.Scanner;

// Даны три целых числа, каждое записано в отдельной строке.
// Выведите наибольшее из данных чисел (программа должна вывести ровно одно целое число).

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int value1 = in.nextInt();
        int value2 = in.nextInt();
        int value3 = in.nextInt();

        int res = 0;
        if (value1 >= value2 && value1 >= value3) res = value1;
        if (value2 >= value1 && value2 >= value3) res = value2;
        if (value3 >= value2 && value3 >= value1) res = value3;
        System.out.printf("%d", res);

        in.close();
    }
}