package org.fing.middleware.integration;

import org.fing.middleware.datatypes.PagoInfo;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * Created by pablo on 11/09/15.
 */
public class FacturasTransformerBean {


    public Message<String> tranformarFacturas(Message<PagoInfo> pagoInfoMessage) {
        System.out.println("$$$  tranformarFacturas ");
        String requestXml =
                "<FahrenheitToCelsius xmlns=\"http://www.w3schools.com/webservices/\">" +
                        "<Fahrenheit>90.0</Fahrenheit>" +
                        "</FahrenheitToCelsius>";

        // Create the Message object
        Message<String> message = MessageBuilder.withPayload(requestXml).build();
        return message;
    }
}
