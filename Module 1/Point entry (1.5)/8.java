import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double x = in.nextDouble();
        double y = in.nextDouble();
        boolean res = false;

        if (x > 0) {
            if (y > 0 && y < 1 && y > x - 1) res = true;
            if (y < 0 && 1 > x * x + y * y) res = true;
        }

        System.out.printf("%s", res ? "YES": "NO");
        in.close();
    }
}