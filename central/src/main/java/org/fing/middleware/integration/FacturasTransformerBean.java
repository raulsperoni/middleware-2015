package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoFacturaInfo;
import org.fing.middleware.datatypes.PagoInfo;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by pablo on 11/09/15.
 */
public class FacturasTransformerBean {


    public Message<PagoFacturaInfo> tranformarFacturas(Message<PagoInfo> pagoInfoMessage) {
        System.out.println("$$$  tranformarFacturas ");

        // Create the Message object
        Long idPago = pagoInfoMessage.getPayload().getPago().getIdentificadorPago();
        //TODO: hacer esto bien
        Short codMoneda = Short.parseShort("2");
        BigDecimal monto = new BigDecimal(pagoInfoMessage.getPayload().getPago().getMonto());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(pagoInfoMessage.getPayload().getFechaCobro());
        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        PagoFacturaInfo pfi = new PagoFacturaInfo(idPago, codMoneda, monto, xmlGregorianCalendar);
        Message<PagoFacturaInfo> message = MessageBuilder.withPayload(pfi)
                .copyHeaders(pagoInfoMessage.getHeaders())
                .build();
        return message;
    }
}
