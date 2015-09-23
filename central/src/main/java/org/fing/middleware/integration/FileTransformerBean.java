package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoInfo;
import org.fing.middleware.datatypes.XmlPagoInfo;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Created by pablo on 11/09/15.
 */
public class FileTransformerBean {

    public Message<File> transformarAFile(Message<PagoInfo> pagoInfoMessage) {

        System.out.println("##### paso a XML");

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

        System.out.println("##### paso a CSV con XSL");
        File csvfile = null;
        try {

            // Set up streams for input, stylesheet, and output.
            // These do not have to come from or go to files. We can also use the
            // javax.xml.transform.{dom,sax} packages use DOM trees and streams of
            // SAX events as sources and sinks for documents and stylesheets.
            //StreamSource stylesheet = new StreamSource(new File(getClass().getClassLoader().getResource("XMLaCSV.xsl").getFile()).getPath());


            String outputString = transform(resultado, getClass().getClassLoader().getResource("XMLaCSV.xsl").getFile());


            SimpleDateFormat sdfFecha = new SimpleDateFormat("yyyymmdd");
            SimpleDateFormat sdfHora = new SimpleDateFormat("HHmmss");
            csvfile = new File("middleware-" + sdfFecha.format(pi.getFechaCobro()) + "-" + sdfHora.format(pi.getFechaCobro()) + "-" + pi.getPago().getIdentificadorPago() + ".csv");
            FileWriter fileWriter = new FileWriter(csvfile);
            BufferedWriter bufferWritter = new BufferedWriter(fileWriter);
            bufferWritter.write(outputString);
            bufferWritter.close();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


        Message<File> message = MessageBuilder.withPayload(csvfile)
                .copyHeaders(pagoInfoMessage.getHeaders())
                .build();

        return message;

    }


    private String transform(String dataXML, String inputXSL) throws TransformerException {
        StringReader reader = new StringReader(dataXML);
        StringWriter writer = new StringWriter();
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(
                new javax.xml.transform.stream.StreamSource(inputXSL));

        transformer.transform(
                new javax.xml.transform.stream.StreamSource(reader),
                new javax.xml.transform.stream.StreamResult(writer));

        return writer.toString();
    }



}
