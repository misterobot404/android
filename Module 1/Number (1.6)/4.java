import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double A = in.nextDouble();
        double B = in.nextDouble();
        double x;

        if (A <= 0 && B <= 0) System.out.printf("%s","no such x");
        if ((A >= 0 && B > 0) || (A > 0 && B >= 0)) System.out.printf("%s","any x");

        if (A < 0 && B > 0) {
            x = B / -A;
            System.out.printf("%.1f<x<%.1f",-x,x);
        }
        if (A > 0 && B < 0) {
            x = -B / A;
            System.out.printf("x<%.1f or x>%.1f",-x,x);
        }

        in.close();
    }
}