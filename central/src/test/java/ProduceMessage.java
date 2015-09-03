import org.fing.middleware.jms.Producer;
import org.junit.Test;

import javax.jms.JMSException;

/**
 * Created by raul on 03/09/15.
 */
public class ProduceMessage {

    @Test
    public void produceMessage() {
        Producer p = null;
        try {
            p = new Producer();
            p.send();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
