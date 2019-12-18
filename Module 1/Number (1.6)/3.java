import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int number = in.nextInt();
        int last_digit = number%10;

        if (last_digit == 0 || (last_digit >= 5 && last_digit <= 9) || (number%100 >= 10 && number%100 <= 20)) System.out.printf("%d %s", number,"TOPTOB");
        else {
            if (last_digit == 1) System.out.printf("%d %s", number, "TOPT");
            if (last_digit >= 2 && last_digit <= 4) System.out.printf("%d %s", number, "TOPTA");
        }
        in.close();
    }
}