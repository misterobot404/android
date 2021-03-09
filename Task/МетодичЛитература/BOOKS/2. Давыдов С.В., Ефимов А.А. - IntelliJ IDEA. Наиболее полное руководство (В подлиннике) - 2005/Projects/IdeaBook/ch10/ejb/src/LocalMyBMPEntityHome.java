package ideabook.ch10;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public interface LocalMyBMPEntityHome extends EJBLocalHome {
    ideabook.ch10.LocalMyBMPEntity findByPrimaryKey(String key) throws FinderException;
}
