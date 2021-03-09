package ideabook.ch14;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */
public class MyTestRunner {
    public static void main(String[] args) {
        new TestRunner().doRun(new TestSuite(SimpleTest.class));
    }
}
