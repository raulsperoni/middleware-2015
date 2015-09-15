package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoInfo;
import org.fing.middleware.datatypes.XmlPagoInfo;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Created by pablo on 11/09/15.
 */
public class XmlTransformerBean {

    public Message<String> transformar(Message<PagoInfo> pagoInfoMessage) {

        System.out.println("  crearXML ");

        PagoInfo pi = pagoInfoMessage.getPayload();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaCobro = simpleDateFormat.format(pi.getFechaCobro());
        simpleDateFormat = new SimpleDateFormat("hh:mm");
        String horaCobro = simpleDateFormat.format(pi.getFechaCobro());
        XmlPagoInfo xmlPagoInfo = new XmlPagoInfo(pi.getIdentificadorCliente(), pi.getPago().getCodigoMoneda(), new BigDecimal(pi.getPago().getMonto()), fechaCobro, horaCobro);

        String resultado = "";
        Writer writer = new StringWriter();
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(XmlPagoInfo.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(xmlPagoInfo, writer);
            resultado = writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Message<String> message = MessageBuilder.withPayload(resultado)
                .copyHeaders(pagoInfoMessage.getHeaders())
                .build();

        return message;

    }
}
