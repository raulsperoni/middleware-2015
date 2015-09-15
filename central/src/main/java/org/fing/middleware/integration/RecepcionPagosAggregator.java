package org.fing.middleware.integration;

import org.fing.middleware.jms.DataLealtad;
import org.fing.middleware.jms.Producer;
import org.fing.middleware.services.ConfirmacionPago;
import org.fing.middleware.services.Pago;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.messaging.Message;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by pablo on 10/09/15.
 */
public class RecepcionPagosAggregator {

    public List<ConfirmacionPago> confirmarPagos(Collection<Message<Pago>> pagos) {

        List<ConfirmacionPago> confirmacion = new ArrayList<ConfirmacionPago>();

        System.out.println("#################### confirmarPAgos ");
        for (Message<Pago> pago : pagos)
            try {

                Producer p = new Producer();
                //TODO: de donde saco el id del cliente aca?
                p.send(new DataLealtad(1, DataLealtad.Monto.USD, pago.getPayload().getMonto(), new Date()));

                ConfirmacionPago c1 = new ConfirmacionPago();
                c1.setDescripcion("prueba agregaaa");
                c1.setIdentificadorPago(pago.getPayload().getIdentificadorPago());
                c1.setResultado("resultado gateway");

                System.out.println("### size: " + pago.getHeaders().get(IntegrationMessageHeaderAccessor.SEQUENCE_SIZE));

                confirmacion.add(c1);

            } catch (JMSException e) {
                e.printStackTrace();
            }
        System.out.println("#################### confirmarPAgos ");


        return confirmacion;

    }


}
