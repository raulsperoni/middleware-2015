package org.fing.middleware.integration;

import org.fing.middleware.services.Pago;

/**
 * Created by pablo on 11/09/15.
 */
public class PagosRouter {

    public String route(Pago pago) {
        String channel = pago.getNombreGestion().toLowerCase();
        System.out.println("### router channel  " + channel);
        return channel.equals("facturas") ? channel+"Channel" : "resultChannel";
    }

}
