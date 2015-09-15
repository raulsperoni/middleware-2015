package org.fing.middleware.integration;

import org.fing.middleware.services.Pago;
import org.fing.middleware.services.TransaccionPago;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul on 10/09/15.
 */

public class SplitterBean {

    public List<Message<Pago>> extraerPagos(Message<TransaccionPago> transaccionPago) {

        System.out.println("### splitter: sucursal " + transaccionPago.getPayload().getNumeroSucursal());

        int size = transaccionPago.getPayload().getPagos().size();
        List<Message<Pago>> mensajes = new ArrayList<Message<Pago>>();
        for(Pago p : transaccionPago.getPayload().getPagos()) {
            Message<Pago> mensaje = MessageBuilder.withPayload(p)
                    .copyHeaders(transaccionPago.getHeaders())
                    .build();
            mensajes.add(mensaje);
        }

        return mensajes;
    }
}
