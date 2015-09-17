package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoInfo;
import org.fing.middleware.services.ConfirmacionPago;
import org.springframework.messaging.Message;

/**
 * Created by raul on 17/09/15.
 */
public class CobroOfflineBean {


    public Message<PagoInfo> confirmarFile(Message<PagoInfo> pagoInfoMessage) {

        //ARMO RESPUESTA
        ConfirmacionPago confirmacionPago = new ConfirmacionPago();
        confirmacionPago.setDescripcion("Archivo recibido");
        confirmacionPago.setIdentificadorPago(pagoInfoMessage.getPayload().getPago().getIdentificadorPago());
        confirmacionPago.setResultado("OK");

        pagoInfoMessage.getPayload().setConfirmacionPago(confirmacionPago);

        return pagoInfoMessage;
    }

}
