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

    public List<Message<PagoInfo>> extraerPagos(Message<TransaccionPago> transaccionPago) throws IllegalArgumentException {

        List<Message<PagoInfo>> mensajes = new ArrayList<Message<PagoInfo>>();

        //saco el payload
        TransaccionPago tp = transaccionPago.getPayload();

        //datos comunes
        Date date;
        try {
            date = tp.getFechaCobro() != null ? tp.getFechaCobro().toGregorianCalendar().getTime() : null;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        if (date == null) {
            throw new IllegalArgumentException("Fecha Incorrecta");
        }

        String formaDePago = tp.getFormaPago();
        if (formaDePago == null || (!formaDePago.equals("Efectivo") && !formaDePago.equals("Cheque") && !formaDePago.equals("Debito"))) {
            throw new IllegalArgumentException("Forma Pago incorrecta");
        }

        if (tp.getPagos().size() == 0) throw new IllegalArgumentException("Sin pagos");

        //Para cada pago
        for (Pago p : tp.getPagos()) {

            //creo el pagoinfo con todos los datos del cliente
            PagoInfo pi = new PagoInfo(p, date, formaDePago, tp.getIdentificadorCliente(), tp.getNumeroSucursal());
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
