package ideabook.ch10;

import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public abstract class MyCMPEntity2Bean implements EntityBean {
    public MyCMPEntity2Bean() {
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

    public abstract LocalMyCMPEntity getMyCMPEntity();

    public abstract void setMyCMPEntity(LocalMyCMPEntity myCMPEntity);

    public abstract String getName();

    public abstract void setName(String value);
}
