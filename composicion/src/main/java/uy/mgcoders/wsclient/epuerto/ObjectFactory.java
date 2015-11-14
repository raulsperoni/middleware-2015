
package uy.mgcoders.wsclient.epuerto;

import javax.xml.bind.annotation.XmlRegistry;


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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.mgcoders.wsclient.epuerto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Lineas }
     * 
     */
    public Lineas createLineas() {
        return new Lineas();
    }

    /**
     * Create an instance of {@link OrdenEpuerto }
     * 
     */
    public OrdenEpuerto createOrdenEpuerto() {
        return new OrdenEpuerto();
    }

    /**
     * Create an instance of {@link ConfirmacionOrden }
     * 
     */
    public ConfirmacionOrden createConfirmacionOrden() {
        return new ConfirmacionOrden();
    }

}
