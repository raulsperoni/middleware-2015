package uy.mgcoders.listener;

import org.fing.middleware.services.ConfirmacionPago;
import org.fing.middleware.services.TransaccionPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.support.channel.BeanFactoryChannelResolver;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo on 02/09/15.
 */
public class ProcesarPagosImpl implements IProcesarPagos {

    //@Autowired
    //private ApplicationContext context;

    public List<ConfirmacionPago> procesarPagos(TransaccionPago pagos) {

        /*
        * En teoria esto está en el "contexto" de spring.
        *
        * */

        ConfirmacionPago c1 = new ConfirmacionPago();
        c1.setDescripcion("prueba descr");
        c1.setIdentificadorPago(10547);
        c1.setResultado("resultado string");

        List<ConfirmacionPago> confirmacion = new ArrayList<ConfirmacionPago>();
        confirmacion.add(c1);

        return confirmacion;
    }

}
