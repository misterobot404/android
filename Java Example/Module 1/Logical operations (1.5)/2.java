import java.util.Scanner;

// На числовой прямой даны два отрезка [−3;5] и [9;15]. Требуется определить, принадлежит ли точка x любому из данных отрезков.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double value = in.nextDouble();

        System.out.printf("%s", (value >= -3 && value <= 5) || (value >= 9 && value <= 15) ? "true" : "false");

        in.close();
    }
}