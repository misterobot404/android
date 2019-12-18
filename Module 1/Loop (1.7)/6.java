import java.util.Scanner;

// Определите тип треугольника (остроугольный, тупоугольный, прямоугольный) с данными сторонами.
// Даны три натуральных числа – стороны треугольника.
// Необходимо вывести одно из слов: right для прямоугольного треугольника,
// acute для остроугольного треугольника,
// obtuse для тупоугольного треугольника или impossible, если входные числа не образуют треугольника.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int d,e,mx,sr,mn;
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        if (a+b<=c || a+c<=b || b+c<=a) System.out.printf("%s", "impossible");
        else {
            mx = Math.max(a, b);
            if (c > mx) mx=c;

            if (a<b) mn=a;
            else mn=b;

            if (c<mn) mn=c;

            sr=a + b + c - mx - mn;
            d=mn * mn + sr * sr;
            e=mx * mx;
            if (d > e) System.out.printf("%s", "acute");
            else if (d<e) System.out.printf("%s", "obtuse");
            else System.out.printf("%s", "right");
        }
        in.close();
    }
}