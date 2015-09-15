package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoInfo;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.messaging.Message;

/**
 * Created by pablo on 11/09/15.
 */
public class PagosRouter {

    public String route(Message<PagoInfo> pagoInfoMessage) {
        PagoInfo pi = pagoInfoMessage.getPayload();
        String channel = pi.getPago().getNombreGestion().toLowerCase();
        System.out.println("### router channel  " + channel);
        System.out.println("### idPago " + pi.getPago().getIdentificadorPago() + " correlationID " + pagoInfoMessage.getHeaders().get(IntegrationMessageHeaderAccessor.CORRELATION_ID));

        return channel.equals("facturas") ? channel+"Channel" : "resultChannel";
    }

}
