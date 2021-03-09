package ideabook.ch10;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public interface LocalMyCMPEntityHome extends EJBLocalHome {
    LocalMyCMPEntity findByPrimaryKey(String key) throws FinderException;
}
