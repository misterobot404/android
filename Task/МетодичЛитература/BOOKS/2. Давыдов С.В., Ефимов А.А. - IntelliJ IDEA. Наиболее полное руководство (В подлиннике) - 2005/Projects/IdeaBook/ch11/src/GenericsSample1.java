package ideabook.ch11;

import java.util.List;
import java.util.Arrays;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */
public class GenericsSample1 {
    public static void main(String[] args) {
        final List <String> argList = Arrays.asList(args);
        for (int i = 0; i < argList.size(); i++) {
            final String arg = argList.get(i);
            System.out.println("arg = " + arg);
        }
    }
}
