package ideabook.ch11;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */
public class BoxingSample {
    public static void main(String[] args) {
        // autoboxing
        final Integer [] intArray = {1, 2, Integer.valueOf(33), 21};

        // autounboxing
        int sum = 0;
        for (Integer integer : intArray) {
            sum += integer;
        }

        System.out.println("sum = " + sum);
    }
}
