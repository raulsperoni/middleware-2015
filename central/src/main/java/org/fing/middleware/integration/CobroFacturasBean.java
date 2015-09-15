package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoFacturaInfo;
import org.fing.middleware.serviceCobroFactura.IServicioCobroDeFacturas;
import org.fing.middleware.serviceCobroFactura.ServicioCobroDeFacturasService;
import org.fing.middleware.serviceCobroFactura.WsResult;
import org.fing.middleware.services.ConfirmacionPago;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import javax.xml.ws.BindingProvider;

/**
 * Created by RSperoni on 15/09/2015.
 */
public class CobroFacturasBean {
    public Message<ConfirmacionPago> cobro(Message<PagoFacturaInfo> pagoInfoMessage) {
        ServicioCobroDeFacturasService servicioCobroDeFacturasService = new ServicioCobroDeFacturasService();

        IServicioCobroDeFacturas port = servicioCobroDeFacturasService.getServicioCobroDeFacturasPort();
        BindingProvider prov = (BindingProvider) port;
        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "admin");
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "admin");

        PagoFacturaInfo pfi = pagoInfoMessage.getPayload();
        WsResult result = port.cobrar(pfi.getIdFactura(), pfi.getCodMoneda(), pfi.getMonto(), pfi.getFechaHoraCobro());
        ConfirmacionPago confirmacionPago = new ConfirmacionPago();
        confirmacionPago.setDescripcion(result.getMensaje());
        confirmacionPago.setIdentificadorPago(result.getIdCobro());
        //TODO: hacer bien
        confirmacionPago.setResultado(result.isStatus() ? "OK" : "FAIL");
        Message<ConfirmacionPago> message = MessageBuilder.withPayload(confirmacionPago)
                .copyHeaders(pagoInfoMessage.getHeaders())
                .build();
        return message;
    }
}
