import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double x = in.nextDouble();
        double y = in.nextDouble();

        System.out.printf("%s", (x > 0 && x < Math.PI && y < Math.sin(x) && y<0.5 && y > 0) ? "YES": "NO");

        in.close();
    }
}
