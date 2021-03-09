package ideabook.ch11;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */
/**
 * Example from Sun Java 5 description
 */
public class EnumSample2 {
    public enum Operation {
      PLUS   { double eval(double x, double y) { return x + y; } },
      MINUS  { double eval(double x, double y) { return x - y; } },
      TIMES  { double eval(double x, double y) { return x * y; } },
      DIVIDE { double eval(double x, double y) { return x / y; } };

      // Do arithmetic op represented by this constant
      abstract double eval(double x, double y);
    }

    public static void main(String[] args) {
        double res = Operation.DIVIDE.eval(10, 5);
        System.out.println("res = " + res);
    }
}
