package uy.mgcoders.listener;

import org.fing.middleware.services.ConfirmacionPago;
import org.fing.middleware.services.TransaccionPago;

import java.util.List;

/**
 * Created by pablo on 02/09/15.
 */
public interface IProcesarPagos {

    List<ConfirmacionPago> procesarPagos(TransaccionPago pagos);

}
