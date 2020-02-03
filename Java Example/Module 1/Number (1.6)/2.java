import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int number1 = in.nextInt();
        int number2 = in.nextInt();
        int number3 = in.nextInt();

        if (number1<=number2) {
            if ((number1 <= number3) && (number2 <= number3)) System.out.printf("%d %d %d", number1, number2, number3);
            if ((number1 <= number3) && (number3 <= number2)) System.out.printf("%d %d %d", number1, number3, number2);
        }
        if (number2 <= number1) {
            if ((number2 <= number3) && (number1 <= number3)) System.out.printf("%d %d %d", number2, number1, number3);
            if ((number2 <= number3) && (number3 <= number1)) System.out.printf("%d %d %d", number2, number3, number1);
        }
        if (number3 <= number1) {
            if ((number3 <= number2) && (number1 <= number2)) System.out.printf("%d %d %d", number3, number1, number2);
            if ((number3 <= number2) && (number2 <= number1)) System.out.printf("%d %d %d", number3, number2, number1);
        }

        in.close();
    }
}