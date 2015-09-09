import org.fing.middleware.jms.DataLealtad;
import org.fing.middleware.jms.Producer;
import org.junit.Test;

import javax.jms.JMSException;
import java.util.Date;

/**
 * Created by raul on 03/09/15.
 */
public class ProduceMessage {

    @Test
    public void produceMessage() {
        Producer p = null;
        try {
            p = new Producer();
            p.send(new DataLealtad(1, DataLealtad.Monto.USD, 1000, new Date()));
            p.send(new DataLealtad(2, DataLealtad.Monto.USD, 1345, new Date()));
            p.send(new DataLealtad(3, DataLealtad.Monto.USD, 34567, new Date()));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
