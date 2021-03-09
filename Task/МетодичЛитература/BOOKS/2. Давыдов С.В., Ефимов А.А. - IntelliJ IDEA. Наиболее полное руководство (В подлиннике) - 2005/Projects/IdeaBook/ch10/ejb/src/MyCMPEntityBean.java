package ideabook.ch10;

import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public abstract class MyCMPEntityBean implements EntityBean {
    public MyCMPEntityBean() {
    }

    public void setEntityContext(EntityContext entityContext) throws EJBException {
    }

    public void unsetEntityContext() throws EJBException {
    }

    public void ejbRemove() throws RemoveException, EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    public void ejbLoad() throws EJBException {
    }

    public void ejbStore() throws EJBException {
    }

    public abstract String getId();

    public abstract void setId(String id);

    public abstract String getMyField();

    public abstract void setMyField(String myField);

    public abstract LocalMyCMPEntity2 getMyCMPEntity1();

    public abstract void setMyCMPEntity1(LocalMyCMPEntity2 myCMPEntity1);
}
