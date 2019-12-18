import java.util.Scanner;

// На числовой прямой дан отрезок [3;8]. Требуется определить, принадлежит ли точка x данному отрезку.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double value = in.nextDouble();

        System.out.printf("%s", value >= 3 && value <= 8 ? "true" : "false");

        in.close();
    }
}