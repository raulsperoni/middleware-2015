package org.fing.middleware.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by raul on 03/09/15.
 */
public class Producer {

    public static String brokerURL = "tcp://localhost:61616";
    public static String queue = "INTROMIDDL2015";
    public final Connection connection;
    public final Session session;
    public final Destination destination;

    public Producer() throws JMSException {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        // Create a Connection
        connection = connectionFactory.createConnection();
        connection.start();
        // Create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Create the destination (Topic or Queue)
        destination = session.createQueue(queue);
    }

    public void send(DataLealtad dataLealtad) throws JMSException {
        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // Create a messages
        String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
        //TextMessage message = session.createTextMessage(text);
        ObjectMessage message = session.createObjectMessage(dataLealtad);
        // Tell the producer to send the message
        System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
        producer.send(message);
    }

    public void close() throws JMSException {
        // Clean up
        session.close();
        connection.close();
    }
}
