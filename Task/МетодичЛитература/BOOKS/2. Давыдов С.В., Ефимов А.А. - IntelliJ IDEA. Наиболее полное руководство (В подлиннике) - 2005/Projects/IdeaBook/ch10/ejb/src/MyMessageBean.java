package ideabook.ch10;

import javax.ejb.MessageDrivenBean;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenContext;
import javax.ejb.CreateException;
import javax.jms.MessageListener;
import javax.jms.Message;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */

public class MyMessageBean implements MessageDrivenBean, MessageListener {
    public MyMessageBean() {
    }

    public void onMessage(Message message) {
    }

    public void ejbRemove() throws EJBException {
    }

    public void setMessageDrivenContext(MessageDrivenContext messageDrivenContext) throws EJBException {
    }

    public void ejbCreate() throws CreateException {
    }
}
