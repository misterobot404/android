import java.util.Scanner;

// Даны 4 целых числа. Требуется определить, есть ли среди этих чисел взаимно противоположные (0 противоположен сам себе).

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int value1 = in.nextInt();
        int value2 = in.nextInt();
        int value3 = in.nextInt();
        int value4 = in.nextInt();
        boolean res = false;

        if (value1 != 0 && ((-value1 == value1) || (-value1 == value2) || (-value1 == value3) || (-value1 == value4))) res = true;
        if (value2 != 0 && ((-value2 == value1) || (-value2 == value2) || (-value2 == value3) || (-value2 == value4))) res = true;
        if (value3 != 0 && ((-value3 == value1) || (-value3 == value2) || (-value3 == value3) || (-value3 == value4))) res = true;
        if (value4 != 0 && ((-value4 == value1) || (-value4 == value2) || (-value4 == value3) || (-value4 == value4))) res = true;

        System.out.printf("%b", res);

        in.close();
    }
}