import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double x = in.nextDouble();
        double y = in.nextDouble();
        boolean res = false;

        if (x <= 0 && y > 2*x*x && y > 1 - x) res = true;
        if (x > 0 && x < 1 && y > 1 - x) res = true;

        System.out.printf("%s", res ? "YES": "NO");
        in.close();
    }
}