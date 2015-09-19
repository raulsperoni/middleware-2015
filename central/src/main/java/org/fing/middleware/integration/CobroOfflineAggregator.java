package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoInfo;
import org.fing.middleware.services.ConfirmacionPago;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.io.File;
import java.util.Collection;

/**
 * Created by raul on 17/09/15.
 */
public class CobroOfflineAggregator {


    public Message<PagoInfo> confirmarFile(Collection<Message> messages) {

        Message<String> fileReplyMessage = null;
        Message<PagoInfo> pagoInfoMessage = null;
        if (messages.size() == 2) {
            for (Message m : messages) {
                if (m.getPayload() instanceof PagoInfo) pagoInfoMessage = m;
                if (m.getPayload() instanceof File) fileReplyMessage = m;
            }
        }

        //ARMO RESPUESTA
        ConfirmacionPago confirmacionPago = new ConfirmacionPago();
        if (fileReplyMessage != null && pagoInfoMessage != null) {
            confirmacionPago.setDescripcion("Archivo recibido");
            confirmacionPago.setIdentificadorPago(pagoInfoMessage.getPayload().getPago().getIdentificadorPago());
            confirmacionPago.setResultado("OK");
            pagoInfoMessage.getPayload().setConfirmacionPago(confirmacionPago);
            return pagoInfoMessage;
        } else {
            confirmacionPago.setDescripcion("No se pudo guardar el archivo");
            confirmacionPago.setIdentificadorPago(pagoInfoMessage.getPayload().getPago().getIdentificadorPago());
            confirmacionPago.setResultado("Error");
            PagoInfo pagoInfo = new PagoInfo(null, null, null, 0, 0);
            pagoInfo.setConfirmacionPago(confirmacionPago);
            Message<PagoInfo> message = MessageBuilder.withPayload(pagoInfo).build();
            return message;
        }

    }

}
