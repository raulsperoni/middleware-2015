package org.fing.middleware.integration;

import org.fing.middleware.services.ConfirmacionPago;
import org.fing.middleware.services.TransaccionPago;
import org.springframework.messaging.handler.annotation.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo on 10/09/15.
 */
public class RecepcionPagosAggregator {

    public List<ConfirmacionPago> confirmarPagos(TransaccionPago pagos,
                                                 @Header(value="RESPONSE_TYPE", required=false) String responseType) {

        System.out.println("#################### confirmarPAgos ");

        ConfirmacionPago c1 = new ConfirmacionPago();
        c1.setDescripcion("prueba agregaaa");
        c1.setIdentificadorPago(112358);
        c1.setResultado("resultado gateway");

        List<ConfirmacionPago> confirmacion = new ArrayList<ConfirmacionPago>();
        confirmacion.add(c1);

        return confirmacion;

    }


}
