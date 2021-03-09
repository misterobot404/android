package ideabook.ch11;

import java.util.List;
import java.util.Arrays;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */
public class ForLoopSample {
    public static void main(String[] args) {
        final List <String> argList = Arrays.asList(args);
        for (String arg : argList) {
            System.out.println("arg = " + arg);
        }
    }
}
