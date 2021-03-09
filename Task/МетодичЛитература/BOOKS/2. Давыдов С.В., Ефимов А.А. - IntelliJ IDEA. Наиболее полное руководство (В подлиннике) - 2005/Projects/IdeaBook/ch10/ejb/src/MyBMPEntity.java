package ideabook.ch10;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public interface MyBMPEntity extends EJBObject {
    String getMyField() throws RemoteException;

    void setMyField(String myField) throws RemoteException;
}
