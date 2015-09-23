package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoInfo;
import org.fing.middleware.services.Pago;
import org.fing.middleware.services.TransaccionPago;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by raul on 10/09/15.
 */

public class SplitterBean {

    public List<Message<PagoInfo>> extraerPagos(Message<TransaccionPago> transaccionPago) {

        List<Message<PagoInfo>> mensajes = new ArrayList<Message<PagoInfo>>();

        //Para cada pago
        for(Pago p : transaccionPago.getPayload().getPagos()) {
            //saco el payload
            TransaccionPago tp = transaccionPago.getPayload();
            //creo el pagoinfo con todos los datos del cliente
            Date date = tp.getFechaCobro() != null ? tp.getFechaCobro().toGregorianCalendar().getTime() : null;
            if (date == null) System.out.println("OJO NO LLEGO LA FECHA!");
            PagoInfo pi = new PagoInfo(p, date, tp.getFormaPago(), tp.getIdentificadorCliente(), tp.getNumeroSucursal());
            //creo el mje
            Message<PagoInfo> mensaje = MessageBuilder.withPayload(pi)
                    .copyHeaders(transaccionPago.getHeaders())
                    .build();
            //lo agrego
            mensajes.add(mensaje);
        }
        return mensajes;
    }
}
