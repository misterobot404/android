import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int number = in.nextInt();

        if (number >= 0 && number/10==0) {
            System.out.printf("%s", "DIGIT");
        } else if(number > 0 && number/10/10==0) {
            System.out.printf("%s", "NUM");
        } else System.out.printf("%s", "OTHER");

        in.close();
    }
}