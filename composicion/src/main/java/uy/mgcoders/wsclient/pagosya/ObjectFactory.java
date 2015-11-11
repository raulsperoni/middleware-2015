
package uy.mgcoders.wsclient.pagosya;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uy.mgcoders.wsclient.pagosya package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RecepcionPagoResponse_QNAME = new QName("http://services.mgcoders.uy/", "recepcionPagoResponse");
    private final static QName _RecepcionPago_QNAME = new QName("http://services.mgcoders.uy/", "recepcionPago");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.mgcoders.wsclient.pagosya
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RecepcionPago }
     * 
     */
    public RecepcionPago createRecepcionPago() {
        return new RecepcionPago();
    }

    /**
     * Create an instance of {@link RecepcionPagoResponse }
     * 
     */
    public RecepcionPagoResponse createRecepcionPagoResponse() {
        return new RecepcionPagoResponse();
    }

    /**
     * Create an instance of {@link ConfirmacionPago }
     * 
     */
    public ConfirmacionPago createConfirmacionPago() {
        return new ConfirmacionPago();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecepcionPagoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mgcoders.uy/", name = "recepcionPagoResponse")
    public JAXBElement<RecepcionPagoResponse> createRecepcionPagoResponse(RecepcionPagoResponse value) {
        return new JAXBElement<RecepcionPagoResponse>(_RecepcionPagoResponse_QNAME, RecepcionPagoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecepcionPago }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mgcoders.uy/", name = "recepcionPago")
    public JAXBElement<RecepcionPago> createRecepcionPago(RecepcionPago value) {
        return new JAXBElement<RecepcionPago>(_RecepcionPago_QNAME, RecepcionPago.class, null, value);
    }

}
