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
import java.util.List;

/**
 * Created by raul on 15/09/15.
 */
public class VentaEntradasBean {

    public Message<PagoInfo> cobrar(Message<PagoInfo> pagoInfoMessage) {
        ConfirmacionPago confirmacionPago = new ConfirmacionPago();
        try {
            ServicioVentaDeEntradasService servicioVentaDeEntradasService = new ServicioVentaDeEntradasService();

            IServicioVentaDeEntradas port = servicioVentaDeEntradasService.getServicioVentaDeEntradasPort();
            BindingProvider prov = (BindingProvider) port;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "admin");
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "admin");

            //CONVIERTO LOS CAMPOS QUE NECESITO
            String codMoneda = pagoInfoMessage.getPayload().getPago().getCodigoMoneda().equals("UYU") ? "854" : "840";
            List<String> otrosDatos = pagoInfoMessage.getPayload().getPago().getDatoAdicional();
            Short cantEntradas = Short.valueOf(otrosDatos.get(0));
            BigDecimal precioUnitario = new BigDecimal(pagoInfoMessage.getPayload().getPago().getMonto());
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
            confirmacionPago.setDescripcion(result.getMensaje());
            confirmacionPago.setIdentificadorPago(result.getIdCobro());
            confirmacionPago.setResultado(result.isStatus() ? "OK" : "Error");
        } catch (Exception e) {
            confirmacionPago.setDescripcion("Error al procesar el pago - Entradas");
            confirmacionPago.setIdentificadorPago(pagoInfoMessage.getPayload().getPago().getIdentificadorPago());
            confirmacionPago.setResultado("Error");
        }

        pagoInfoMessage.getPayload().setConfirmacionPago(confirmacionPago);
        return pagoInfoMessage;
    }
}
