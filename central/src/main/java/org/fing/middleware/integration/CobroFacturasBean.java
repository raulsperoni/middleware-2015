package org.fing.middleware.integration;

import com.sun.xml.ws.client.BindingProviderProperties;
import org.fing.middleware.datatypes.PagoInfo;
import org.fing.middleware.serviceCobroFactura.IServicioCobroDeFacturas;
import org.fing.middleware.serviceCobroFactura.ServicioCobroDeFacturasService;
import org.fing.middleware.serviceCobroFactura.WsResult;
import org.fing.middleware.services.ConfirmacionPago;
import org.springframework.messaging.Message;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by RSperoni on 15/09/2015.
 */
public class CobroFacturasBean {

    public Message<PagoInfo> cobro(Message<PagoInfo> pagoInfoMessage) {
        ConfirmacionPago confirmacionPago = new ConfirmacionPago();
        try {
            ServicioCobroDeFacturasService servicioCobroDeFacturasService = new ServicioCobroDeFacturasService();

            IServicioCobroDeFacturas port = servicioCobroDeFacturasService.getServicioCobroDeFacturasPort();
            BindingProvider prov = (BindingProvider) port;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "admin");
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "admin");

            //CONVIERTO LOS CAMPOS QUE NECESITO
            Long idPago = pagoInfoMessage.getPayload().getPago().getIdentificadorPago();
            Short codMoneda; //= pagoInfoMessage.getPayload().getPago().getCodigoMoneda().equals("UYU") ? (short) 1 : (short) 2;
            switch (pagoInfoMessage.getPayload().getPago().getCodigoMoneda()) {
                case "UYU":
                    codMoneda = (short) 1;
                    break;
                case "USD":
                    codMoneda = (short) 2;
                    break;
                default:
                    codMoneda = (short) 3;
            }
            BigDecimal monto = new BigDecimal(pagoInfoMessage.getPayload().getPago().getMonto());
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(pagoInfoMessage.getPayload().getFechaCobro());
            XMLGregorianCalendar xmlGregorianCalendar = null;
            try {
                xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }

            //INVOCO WS
            WsResult result = port.cobrar(idPago, codMoneda, monto, xmlGregorianCalendar);

            //ARMO RESPUESTA
            confirmacionPago.setDescripcion(result.getMensaje());
            confirmacionPago.setIdentificadorPago(result.getIdCobro());
            confirmacionPago.setResultado(result.isStatus() ? "OK" : "Error");

        } catch (Exception e) {
            confirmacionPago.setDescripcion("Error al procesar el pago - Facturas");
            confirmacionPago.setIdentificadorPago(pagoInfoMessage.getPayload().getPago().getIdentificadorPago());
            confirmacionPago.setResultado("Error");
        }

        pagoInfoMessage.getPayload().setConfirmacionPago(confirmacionPago);
        return pagoInfoMessage;
    }
}
