package org.fing.middleware.services;

import javax.xml.ws.Endpoint;

/**
 * Created by raul on 30/08/15.
 */
public class ServicioCobroDeFacturasPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9998/ws/ServicioCobroDeFacturas", new ServicioCobroDeFacturas());
    }
}
