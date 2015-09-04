package org.fing.middleware.servlet;

import org.fing.middleware.jms.Consumer;

import javax.jms.JMSException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by raul on 03/09/15.
 */
@WebListener
public class Config implements ServletContextListener {

    //MessageListener preparado para recibir.
    Consumer consumer;

    //Al inicio de la app
    public void contextInitialized(ServletContextEvent event) {

        try {
            consumer = new Consumer();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    //Al final de la app.
    public void contextDestroyed(ServletContextEvent event) {
        try {
            consumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}