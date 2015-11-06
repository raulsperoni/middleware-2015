
package uy.mgcoders.wsclient.stock;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uy.mgcoders.wsclient.stock package. 
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

    private final static QName _Reserva_QNAME = new QName("http://services.mgcoders.uy/", "Reserva");
    private final static QName _Resultado_QNAME = new QName("http://services.mgcoders.uy/", "resultado");
    private final static QName _ResultadoAnulacion_QNAME = new QName("http://services.mgcoders.uy/", "resultadoAnulacion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.mgcoders.wsclient.stock
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResultadoAnulacion }
     * 
     */
    public ResultadoAnulacion createResultadoAnulacion() {
        return new ResultadoAnulacion();
    }

    /**
     * Create an instance of {@link Reserva }
     * 
     */
    public Reserva createReserva() {
        return new Reserva();
    }

    /**
     * Create an instance of {@link Resultado }
     * 
     */
    public Resultado createResultado() {
        return new Resultado();
    }

    /**
     * Create an instance of {@link Producto }
     * 
     */
    public Producto createProducto() {
        return new Producto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Reserva }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mgcoders.uy/", name = "Reserva")
    public JAXBElement<Reserva> createReserva(Reserva value) {
        return new JAXBElement<Reserva>(_Reserva_QNAME, Reserva.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Resultado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mgcoders.uy/", name = "resultado")
    public JAXBElement<Resultado> createResultado(Resultado value) {
        return new JAXBElement<Resultado>(_Resultado_QNAME, Resultado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultadoAnulacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mgcoders.uy/", name = "resultadoAnulacion")
    public JAXBElement<ResultadoAnulacion> createResultadoAnulacion(ResultadoAnulacion value) {
        return new JAXBElement<ResultadoAnulacion>(_ResultadoAnulacion_QNAME, ResultadoAnulacion.class, null, value);
    }

}
