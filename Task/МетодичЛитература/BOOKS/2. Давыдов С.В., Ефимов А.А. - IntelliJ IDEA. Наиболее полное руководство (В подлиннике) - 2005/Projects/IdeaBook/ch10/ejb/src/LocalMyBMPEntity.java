package ideabook.ch10;

import javax.ejb.EJBLocalObject;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public interface LocalMyBMPEntity extends EJBLocalObject {
    String getMyField();

    void setMyField(String myField);
}
