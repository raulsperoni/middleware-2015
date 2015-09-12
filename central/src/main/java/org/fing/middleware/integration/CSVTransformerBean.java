package org.fing.middleware.integration;

import org.fing.middleware.services.Pago;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * Created by pablo on 11/09/15.
 */
public class CSVTransformerBean {

    public Message<String> crearCSV(Pago pago) {

        System.out.println("  crearCSV ");
        String csv = "estos es csv";
        Message<String> message = MessageBuilder.withPayload(csv).build();

        return message;

    }
}
