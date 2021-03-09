package ideabook.ch11;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */
public class VarargSample {
    public static void printAll (Object... params) {
        for (int i = 0; i < params.length; i++) {
            Object param = params[i];
            System.out.println("param = " + param);
        }
    }

    public static void main(String[] args) {
        printAll("param1", 22, "param3");
    }
}
