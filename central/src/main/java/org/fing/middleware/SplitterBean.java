package org.fing.middleware;

import org.fing.middleware.services.Pago;
import org.fing.middleware.services.TransaccionPago;
import org.springframework.integration.annotation.Splitter;

import java.util.List;

/**
 * Created by raul on 10/09/15.
 */

public class SplitterBean {

    @Splitter
    public List<Pago> extraerPagos(TransaccionPago transaccionPago) {
        return transaccionPago.getPagos();
    }
}
