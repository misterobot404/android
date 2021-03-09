package ideabook.ch10;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public interface MySessionHome extends EJBHome {
    MySession create() throws RemoteException, CreateException;
}
