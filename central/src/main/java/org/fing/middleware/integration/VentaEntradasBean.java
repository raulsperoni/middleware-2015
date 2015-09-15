package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoInfo;
import org.fing.middleware.serviceVentaEntradas.IServicioVentaDeEntradas;
import org.fing.middleware.serviceVentaEntradas.ServicioVentaDeEntradasService;
import org.fing.middleware.serviceVentaEntradas.WsResult;
import org.fing.middleware.services.ConfirmacionPago;
import org.springframework.messaging.Message;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by raul on 15/09/15.
 */
public class VentaEntradasBean {

    public Message<PagoInfo> cobrar(Message<PagoInfo> pagoInfoMessage) {
        ServicioVentaDeEntradasService servicioVentaDeEntradasService = new ServicioVentaDeEntradasService();

        IServicioVentaDeEntradas port = servicioVentaDeEntradasService.getServicioVentaDeEntradasPort();
        BindingProvider prov = (BindingProvider) port;
        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "admin");
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "admin");

        //CONVIERTO LOS CAMPOS QUE NECESITO
        String codMoneda = pagoInfoMessage.getPayload().getPago().getCodigoMoneda().equals("UYU") ? "854" : "840";
        Long idPago = pagoInfoMessage.getPayload().getPago().getIdentificadorPago();
        //TODO: WTF!
        Short cantEntradas = 12;
        BigDecimal precioUnitario = new BigDecimal("22");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(pagoInfoMessage.getPayload().getFechaCobro());
        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        //INVOCO WS
        WsResult result = port.cobrar(cantEntradas, codMoneda, precioUnitario, xmlGregorianCalendar);

        //ARMO RESPUESTA
        ConfirmacionPago confirmacionPago = new ConfirmacionPago();
        confirmacionPago.setDescripcion(result.getMensaje());
        confirmacionPago.setIdentificadorPago(result.getIdCobro());
        //TODO: ojo errores
        confirmacionPago.setResultado(result.isStatus() ? "OK" : "Error");

        pagoInfoMessage.getPayload().setConfirmacionPago(confirmacionPago);
        return pagoInfoMessage;
    }
}
