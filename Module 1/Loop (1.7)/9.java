
import java.util.Scanner;

// Напишите программу, которая вводит с клавиатуры номер месяца и определяет, сколько дней в этом месяце.
// При вводе неверного номера месяца должно быть выведено сообщение об ошибке. Считается, что год невисокосный.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int number = in.nextInt();

        switch(number) {
            case 1:
                System.out.printf("%d", 31);
                break;
            case 2:
                System.out.printf("%d", 28);
                break;
            case 3:
                System.out.printf("%d", 31);
                break;
            case 4:
                System.out.printf("%d", 30);
                break;
            case 5:
                System.out.printf("%d", 31);
                break;
            case 6:
                System.out.printf("%d", 30);
                break;
            case 7:
                System.out.printf("%d", 31);
                break;
            case 8:
                System.out.printf("%d", 31);
                break;
            case 9:
                System.out.printf("%d", 30);
                break;
            case 10:
                System.out.printf("%d", 31);
                break;
            case 11:
                System.out.printf("%d", 30);
                break;
            case 12:
                System.out.printf("%d", 31);
                break;
            default:
                System.out.printf("%d", 0);
        }

        in.close();
    }
}
