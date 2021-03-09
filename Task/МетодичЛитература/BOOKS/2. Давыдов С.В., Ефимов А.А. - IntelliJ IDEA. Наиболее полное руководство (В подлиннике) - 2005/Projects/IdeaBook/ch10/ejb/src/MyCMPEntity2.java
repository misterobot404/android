package ideabook.ch10;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public interface MyCMPEntity2 extends EJBObject {
    String getId() throws RemoteException;

    void setId(String id) throws RemoteException;
}
