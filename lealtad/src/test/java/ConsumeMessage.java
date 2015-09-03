import org.fing.middleware.jms.Consumer;
import org.junit.Test;

import javax.jms.JMSException;

/**
 * Created by raul on 03/09/15.
 */
public class ConsumeMessage {

    @Test
    public void Consume() {
        try {
            Consumer c = new Consumer();
            Thread.sleep(600l);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
