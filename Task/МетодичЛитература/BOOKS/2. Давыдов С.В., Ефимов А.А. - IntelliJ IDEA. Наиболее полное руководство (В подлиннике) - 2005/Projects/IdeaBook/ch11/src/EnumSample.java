package ideabook.ch11;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */
public class EnumSample {
    enum Index { red, green, blue };
    enum State { inc, dec, none };

    Index index;
    State state;

    public static void main(String[] args) {
        Index idx = Index.blue;
    }
}
