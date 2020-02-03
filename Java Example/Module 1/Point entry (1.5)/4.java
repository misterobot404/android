import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double x = in.nextDouble();
        double y = in.nextDouble();
        boolean res = false;

        if (y < 0 && y > x*x - 2) res = true;
        if (y > 0 && x < 0 && y > x*x - 2 && y < -x) res = true;
        if (y > 0 && x > 0 && y > x*x - 2 && y < x) res = true;

        System.out.printf("%s", res ? "YES": "NO");
        in.close();
    }
}