import java.util.Scanner;

// На числовой прямой даны два отрезка [−2;3] и [6;9]. Требуется определить, что точка x НЕ принадлежит ни одному из данных отрезков.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double value = in.nextDouble();
        boolean b = true;

        if (value >= -2 && value <= 3) b = false;
        if (value >= 6 && value <= 9) b = false;

        System.out.printf("%b", b);

        in.close();
    }
}