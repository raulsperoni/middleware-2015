package org.fing.middleware.integration;

import org.fing.middleware.services.ConfirmacionPago;
import org.fing.middleware.services.TransaccionPago;

import java.util.List;

/**
 * Created by pablo on 10/09/15.
 */
public interface RecepcionPagosGateway {

    List<ConfirmacionPago> procesarPagos(TransaccionPago pagos);

}
