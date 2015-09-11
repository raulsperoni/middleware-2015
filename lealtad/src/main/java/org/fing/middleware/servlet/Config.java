package org.fing.middleware.servlet;

import org.fing.middleware.beans.Usuario;
import org.fing.middleware.jms.Consumer;
import org.fing.middleware.jms.DataLealtad;

import javax.jms.JMSException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;

/**
 * Created by raul on 03/09/15.
 */
@WebListener
public class Config implements ServletContextListener {

    //Singleton
    private static Config _instance;
    //Mensajes
    private final HashMap<Long, Usuario> mensajes = new HashMap<Long, Usuario>();
    //MessageListener preparado para recibir.
    Consumer consumer;
    private ServletContext context = null;

    public static Config getInstance() {
        return _instance;
    }

    //Al inicio de la app
    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();
        _instance = this;

        try {
            consumer = new Consumer() {
                @Override
                public void guardarMensaje(DataLealtad mensaje) {
                    Usuario u = null;
                    //Todavia no existe el usuario
                    if (mensajes.get(mensaje.getIdentificadorCliente()) == null) {
                        //Nuevo Usuario
                        u = new Usuario(mensaje.getIdentificadorCliente());
                        //Lo agrego a la estructura
                        mensajes.put(mensaje.getIdentificadorCliente(), u);
                    } else {
                        //Ya existe.
                        u = mensajes.get(mensaje.getIdentificadorCliente());
                    }
                    //Agrego el pago, y los puntos.
                    u.addPago(mensaje);
                }
            };
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    //Al final de la app.
    public void contextDestroyed(ServletContextEvent event) {
        context = null;
        try {
            consumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Long, Usuario> getMensajes() {
        return mensajes;
    }
}