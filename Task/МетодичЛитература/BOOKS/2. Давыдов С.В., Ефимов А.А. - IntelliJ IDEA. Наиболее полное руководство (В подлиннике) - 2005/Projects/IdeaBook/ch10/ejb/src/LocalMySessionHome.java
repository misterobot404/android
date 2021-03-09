package ideabook.ch10;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public interface LocalMySessionHome extends EJBLocalHome {
    LocalMySession create() throws CreateException;
}
