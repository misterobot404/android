package ideabook.ch10;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public interface MyBMPEntityHome extends EJBHome {
    ideabook.ch10.MyBMPEntity findByPrimaryKey(String key) throws RemoteException, FinderException;
}
