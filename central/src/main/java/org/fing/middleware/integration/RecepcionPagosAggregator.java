package org.fing.middleware.integration;

import org.fing.middleware.jms.DataLealtad;
import org.fing.middleware.jms.Producer;
import org.fing.middleware.services.ConfirmacionPago;
import org.fing.middleware.services.Pago;
import org.fing.middleware.services.TransaccionPago;
import org.springframework.messaging.handler.annotation.Header;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by pablo on 10/09/15.
 */
public class RecepcionPagosAggregator {

    public List<ConfirmacionPago> confirmarPagos(Collection<Pago> pagos) {

        System.out.println("#################### confirmarPAgos ");
        Producer p = null;
        try {
            p = new Producer();
            p.send(new DataLealtad(new Random().nextInt(), "USD", 100, null));
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.out.println("#################### confirmarPAgos ");

        ConfirmacionPago c1 = new ConfirmacionPago();
        c1.setDescripcion("prueba agregaaa");
        c1.setIdentificadorPago(112358);
        c1.setResultado("resultado gateway");

        List<ConfirmacionPago> confirmacion = new ArrayList<ConfirmacionPago>();
        confirmacion.add(c1);

        return confirmacion;

    }


}
