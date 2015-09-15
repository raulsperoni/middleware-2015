package org.fing.middleware.integration;

import org.fing.middleware.services.Pago;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.messaging.Message;

/**
 * Created by pablo on 11/09/15.
 */
public class PagosRouter {

    public String route(Message<Pago> pago) {
        String channel = pago.getPayload().getNombreGestion().toLowerCase();
        System.out.println("### router channel  " + channel);
        System.out.println("### idPago " + pago.getPayload().getIdentificadorPago() + " correlationID " + pago.getHeaders().get(IntegrationMessageHeaderAccessor.CORRELATION_ID));

        return channel.equals("facturas") ? channel+"Channel" : "resultChannel";
    }

}
