package org.fing.middleware.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by raul on 03/09/15.
 */
public class Consumer implements MessageListener {

    public static String brokerURL = "tcp://localhost:61616";
    public static String queue = "INTROMIDDL2015";
    private final ConnectionFactory factory;
    private final Connection connection;
    private final Session session;
    private final MessageConsumer consumer;

    public Consumer() throws JMSException {
        factory = new ActiveMQConnectionFactory(brokerURL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queue);
        consumer = session.createConsumer(destination);
        consumer.setMessageListener(this);
    }

    public void onMessage(Message message) {

        try {
            if (message instanceof TextMessage) {
                TextMessage txtMessage = (TextMessage) message;
                System.out.println("Message received: " + txtMessage.getText());
            } else {
                System.out.println("Invalid message received.");
            }
        } catch (JMSException e) {
            System.out.println("Caught:" + e);
            e.printStackTrace();
        }
    }

    public void close() throws JMSException {
        // Clean up
        session.close();
        connection.close();
    }


}
