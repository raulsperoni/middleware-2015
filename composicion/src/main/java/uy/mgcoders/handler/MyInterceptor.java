package uy.mgcoders.handler;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.MessageSenderInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.Destination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pablo on 10/30/15.
 */
public class MyInterceptor extends MessageSenderInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public void handleMessage(Message message) throws Fault{

        Destination destination = message.getDestination();

        logger.info("handleMessage .............................. ldkflksjdfl");
        logger.info("address " + destination.getAddress().getAddress().getValue().toString());

        //Exception exception = new Exception("cortaaa tooododod");
        //throw new Fault(exception);
        //super.handleMessage(message);
    }

}
