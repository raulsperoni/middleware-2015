import org.fing.middleware.jms.DataLealtad;
import org.fing.middleware.jms.Producer;
import org.junit.Test;

import javax.jms.JMSException;
import java.util.Random;

/**
 * Created by raul on 03/09/15.
 */
public class ProduceMessage {

    @Test
    public void produceMessage() {
        Producer p = null;
        try {
            p = new Producer();
            p.send(new DataLealtad(new Random().nextInt(), DataLealtad.Monto.USD, 100, null));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
