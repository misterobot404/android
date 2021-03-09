package ideabook.ch14;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */
public class StringConcatenator {
    public static String concatenate (String[] strings) {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            buffer.append(strings[i]);
        }
        return buffer.toString();
    }
}
