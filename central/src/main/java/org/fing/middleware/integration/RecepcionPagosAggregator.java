package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoInfo;
import org.fing.middleware.jms.DataLealtad;
import org.fing.middleware.jms.Producer;
import org.fing.middleware.services.ConfirmacionPago;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.messaging.Message;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by pablo on 10/09/15.
 */
public class RecepcionPagosAggregator {

    public List<ConfirmacionPago> confirmarPagos(Collection<Message<PagoInfo>> pagosMessages) {

        List<ConfirmacionPago> confirmacion = new ArrayList<ConfirmacionPago>();

        System.out.println("#################### confirmarPAgos ");
        for (Message<PagoInfo> pagoInfoMessage : pagosMessages)
            try {

                PagoInfo pagoInfo = pagoInfoMessage.getPayload();

                //MANDO JMS LEALTAD
                Producer p = new Producer();
                DataLealtad.Moneda moneda = pagoInfo.getPago().getCodigoMoneda().equals("USD") ? DataLealtad.Moneda.USD : DataLealtad.Moneda.UYU;
                p.send(new DataLealtad(pagoInfo.getIdentificadorCliente(), moneda, pagoInfo.getPago().getMonto(), pagoInfo.getFechaCobro()));


                System.out.println("### size: " + pagoInfoMessage.getHeaders().get(IntegrationMessageHeaderAccessor.SEQUENCE_SIZE));

                confirmacion.add(pagoInfo.getConfirmacionPago());

            } catch (JMSException e) {
                e.printStackTrace();
            }
        System.out.println("#################### confirmarPAgos ");


        return confirmacion;

    }


}
