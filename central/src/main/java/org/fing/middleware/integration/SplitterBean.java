package org.fing.middleware.integration;

import org.fing.middleware.services.Pago;
import org.fing.middleware.services.TransaccionPago;
import org.springframework.integration.annotation.Splitter;

import java.util.List;

/**
 * Created by raul on 10/09/15.
 */

public class SplitterBean {

    public List<Pago> extraerPagos(TransaccionPago transaccionPago) {

        System.out.println("### splitter: sucursal " + transaccionPago.getNumeroSucursal());
        return transaccionPago.getPagos();
    }
}
