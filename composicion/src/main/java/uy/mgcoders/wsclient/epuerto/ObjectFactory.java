
package uy.mgcoders.wsclient.epuerto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uy.mgcoders.wsclient.epuerto package. 
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

    private final static QName _AnularOrden_QNAME = new QName("http://services.mgcoders.uy/", "anularOrden");
    private final static QName _ColocarOrdenResponse_QNAME = new QName("http://services.mgcoders.uy/", "colocarOrdenResponse");
    private final static QName _ColocarOrden_QNAME = new QName("http://services.mgcoders.uy/", "colocarOrden");
    private final static QName _AnularOrdenResponse_QNAME = new QName("http://services.mgcoders.uy/", "anularOrdenResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.mgcoders.wsclient.epuerto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AnularOrdenResponse }
     * 
     */
    public AnularOrdenResponse createAnularOrdenResponse() {
        return new AnularOrdenResponse();
    }

    /**
     * Create an instance of {@link ColocarOrden }
     * 
     */
    public ColocarOrden createColocarOrden() {
        return new ColocarOrden();
    }

    /**
     * Create an instance of {@link AnularOrden }
     * 
     */
    public AnularOrden createAnularOrden() {
        return new AnularOrden();
    }

    /**
     * Create an instance of {@link ColocarOrdenResponse }
     * 
     */
    public ColocarOrdenResponse createColocarOrdenResponse() {
        return new ColocarOrdenResponse();
    }

    /**
     * Create an instance of {@link ConfirmacionOrden }
     * 
     */
    public ConfirmacionOrden createConfirmacionOrden() {
        return new ConfirmacionOrden();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnularOrden }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mgcoders.uy/", name = "anularOrden")
    public JAXBElement<AnularOrden> createAnularOrden(AnularOrden value) {
        return new JAXBElement<AnularOrden>(_AnularOrden_QNAME, AnularOrden.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ColocarOrdenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mgcoders.uy/", name = "colocarOrdenResponse")
    public JAXBElement<ColocarOrdenResponse> createColocarOrdenResponse(ColocarOrdenResponse value) {
        return new JAXBElement<ColocarOrdenResponse>(_ColocarOrdenResponse_QNAME, ColocarOrdenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ColocarOrden }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mgcoders.uy/", name = "colocarOrden")
    public JAXBElement<ColocarOrden> createColocarOrden(ColocarOrden value) {
        return new JAXBElement<ColocarOrden>(_ColocarOrden_QNAME, ColocarOrden.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnularOrdenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mgcoders.uy/", name = "anularOrdenResponse")
    public JAXBElement<AnularOrdenResponse> createAnularOrdenResponse(AnularOrdenResponse value) {
        return new JAXBElement<AnularOrdenResponse>(_AnularOrdenResponse_QNAME, AnularOrdenResponse.class, null, value);
    }

}
