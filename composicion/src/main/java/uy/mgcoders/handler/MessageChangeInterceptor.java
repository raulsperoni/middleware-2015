package uy.mgcoders.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.binding.soap.interceptor.SoapPreProtocolOutInterceptor;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;

/**
 * http://www.mastertheboss.com/jboss-web-services/apache-cxf-interceptors
 * http://stackoverflow.com/questions/6915428/how-to-modify-the-raw-xml-message-of-an-outbound-cxf-request
 * 
 */
public class MessageChangeInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MessageChangeInterceptor.class);

    public MessageChangeInterceptor() {
        super(Phase.PRE_STREAM);
        addBefore(SoapPreProtocolOutInterceptor.class.getName());
    }

    public void handleMessage(Message message) {
        boolean isOutbound = false;
        isOutbound = message == message.getExchange().getOutMessage()
                || message == message.getExchange().getOutFaultMessage();

        if (isOutbound) {
            OutputStream os = message.getContent(OutputStream.class);

            CachedStream cs = new CachedStream();
            message.setContent(OutputStream.class, cs);

            message.getInterceptorChain().doIntercept(message);

            try {
                cs.flush();
                IOUtils.closeQuietly(cs);
                CachedOutputStream csnew = (CachedOutputStream) message.getContent(OutputStream.class);

                String currentEnvelopeMessage = IOUtils.toString(csnew.getInputStream(), "UTF-8");
                csnew.flush();
                IOUtils.closeQuietly(csnew);

                logger.info(" *********** pasa por interceptor modifica el soap **************");

                System.out.println(currentEnvelopeMessage);
                String res = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.mgcoders.uy/\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <ser:confirmarOrden>\n" +
                        "         <resultado>\n" +
                        "            <idCompra>33444888888888</idCompra>\n" +
                        "            <codigo>OK</codigo>\n" +
                        "            <!--Optional:-->\n" +
                        "            <descripcion>modificado mensaje desde interceptor</descripcion>\n" +
                        "         </resultado>\n" +
                        "      </ser:confirmarOrden>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>"; //changeOutboundMessage(currentEnvelopeMessage);

                res = res != null ? res : currentEnvelopeMessage;


                InputStream replaceInStream = IOUtils.toInputStream(res, "UTF-8");

                IOUtils.copy(replaceInStream, os);
                replaceInStream.close();
                IOUtils.closeQuietly(replaceInStream);

                os.flush();
                message.setContent(OutputStream.class, os);
                IOUtils.closeQuietly(os);

            } catch (IOException ioe) {
                //getLogger().warn("Unable to perform change.", ioe);
                throw new RuntimeException(ioe);
            }
        }
    }

    public void handleFault(Message message) {
    }

    private class CachedStream extends CachedOutputStream {
        public CachedStream() {
            super();
        }

        protected void doFlush() throws IOException {
            currentStream.flush();
        }

        protected void doClose() throws IOException {
        }

        protected void onWrite() throws IOException {
        }
    }
}
