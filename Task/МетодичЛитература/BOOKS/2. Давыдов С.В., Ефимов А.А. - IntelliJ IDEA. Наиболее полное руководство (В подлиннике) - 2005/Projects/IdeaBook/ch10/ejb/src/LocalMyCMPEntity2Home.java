package ideabook.ch10;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public interface LocalMyCMPEntity2Home extends EJBLocalHome {
    LocalMyCMPEntity2 findByPrimaryKey(String key) throws FinderException;
}
