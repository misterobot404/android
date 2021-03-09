package ideabook.ch10;

import javax.ejb.*;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public class MyBMPEntityBean implements EntityBean {
    public MyBMPEntityBean() {
    }

    public String ejbFindByPrimaryKey(String key) throws FinderException {
        return null;
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

    String getMyField() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    void setMyField(String myField) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
