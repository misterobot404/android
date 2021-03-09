package ideabook.ch10;

import javax.ejb.EJBLocalObject;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public interface LocalMyCMPEntity extends EJBLocalObject {
    String getId();

    void setId(String id);

    String getMyField();

    void setMyField(String myField);

    LocalMyCMPEntity2 getMyCMPEntity1();

    void setMyCMPEntity1(LocalMyCMPEntity2 myCMPEntity1);
}
