package uy.mgcoders.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by pablo on 10/29/15.
 */
public class CustomSOAPHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger logger = LoggerFactory.getLogger(CustomSOAPHandler.class);

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        try {
            SOAPMessage soapMessage = context.getMessage();
            SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
            SOAPHeader soapHeader = soapEnvelope.getHeader();

            Iterator it = soapHeader.examineAllHeaderElements();
            if(it != null) {
                logger.info("if != null");
                while (it.hasNext()) {
                    Node node = (Node)it.next();
                    logger.info("Node: " + node.getValue());
                    NodeList nodeList = node.getChildNodes();
                    if(nodeList != null) {
                        for(int i = 0; i < nodeList.getLength(); i++) {
                            Node node1 = (Node)nodeList.item(i);
                            logger.info("node1: " + node1.getValue());
                        }
                    }
                }
            }


            logger.info("###########   handleMessage");
        } catch (SOAPException e) {

        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        logger.info("#########   handleFault");
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
