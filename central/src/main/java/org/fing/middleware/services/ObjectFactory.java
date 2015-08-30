
package org.fing.middleware.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.fing.middleware.services package. 
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

    private final static QName _RecepcionPagos_QNAME = new QName("http://services.middleware.fing.org/", "recepcionPagos");
    private final static QName _RecepcionPagosResponse_QNAME = new QName("http://services.middleware.fing.org/", "recepcionPagosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.fing.middleware.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RecepcionPagos }
     * 
     */
    public RecepcionPagos createRecepcionPagos() {
        return new RecepcionPagos();
    }

    /**
     * Create an instance of {@link RecepcionPagosResponse }
     * 
     */
    public RecepcionPagosResponse createRecepcionPagosResponse() {
        return new RecepcionPagosResponse();
    }

    /**
     * Create an instance of {@link TransaccionPago }
     * 
     */
    public TransaccionPago createTransaccionPago() {
        return new TransaccionPago();
    }

    /**
     * Create an instance of {@link Pago }
     * 
     */
    public Pago createPago() {
        return new Pago();
    }

    /**
     * Create an instance of {@link ConfirmacionTransaccion }
     * 
     */
    public ConfirmacionTransaccion createConfirmacionTransaccion() {
        return new ConfirmacionTransaccion();
    }

    /**
     * Create an instance of {@link ConfirmacionPago }
     * 
     */
    public ConfirmacionPago createConfirmacionPago() {
        return new ConfirmacionPago();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecepcionPagos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.middleware.fing.org/", name = "recepcionPagos")
    public JAXBElement<RecepcionPagos> createRecepcionPagos(RecepcionPagos value) {
        return new JAXBElement<RecepcionPagos>(_RecepcionPagos_QNAME, RecepcionPagos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecepcionPagosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.middleware.fing.org/", name = "recepcionPagosResponse")
    public JAXBElement<RecepcionPagosResponse> createRecepcionPagosResponse(RecepcionPagosResponse value) {
        return new JAXBElement<RecepcionPagosResponse>(_RecepcionPagosResponse_QNAME, RecepcionPagosResponse.class, null, value);
    }

}
