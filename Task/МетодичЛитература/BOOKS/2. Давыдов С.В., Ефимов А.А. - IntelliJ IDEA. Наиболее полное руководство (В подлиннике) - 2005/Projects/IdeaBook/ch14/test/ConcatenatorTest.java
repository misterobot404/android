package ideabook.ch14;

import junit.framework.TestCase;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */
public class ConcatenatorTest extends TestCase {
    private static final String STR_1 = "abc";
    private static final String STR_2 = "def";
    private static final String STR_3 = "ghi";
    private String concatenation;

    public void test1 () {
        concatenation = "abcdefghi";
        assertEquals("Concatenation of \"" + STR_1 +
                "\", \"" + STR_2 +
                "\" and \"" + STR_3 +
                "\" don't get the \"" + concatenation +
                "\"", concatenation,
                StringConcatenator.concatenate(new String [] {STR_1, STR_2, STR_3}));
    }

    public void test2 () {
        concatenation = "ghidefabc";
        assertEquals("Concatenation of \"" + STR_3 +
                "\", \"" + STR_2 +
                "\" and \"" + STR_1 +
                "\" don't get the \"" + concatenation +
                "\"", concatenation,
                StringConcatenator.concatenate(new String [] {STR_3, STR_2, STR_1}));
    }

    public void test3 () {
        concatenation = null;
        assertEquals("Concatenation of \"" + STR_2 +
                "\", \"" + STR_1 +
                "\" and \"" + STR_3 +
                "\" don't get the \"" + concatenation +
                "\"", concatenation,
                StringConcatenator.concatenate(new String [] {STR_2, STR_1, STR_3}));
    }

    public void test4 () {
        concatenation = null;
        assertEquals("Concatenation of null don't get the null" + concatenation +
                "\"", concatenation,
                StringConcatenator.concatenate(null));
    }
}
