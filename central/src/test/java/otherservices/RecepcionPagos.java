
package otherservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recepcionPagos complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="recepcionPagos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://services.middleware.fing.org/}transaccionPago" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recepcionPagos", propOrder = {
        "arg0"
})
public class RecepcionPagos {

    protected TransaccionPago arg0;

    /**
     * Obtiene el valor de la propiedad arg0.
     *
     * @return possible object is
     * {@link TransaccionPago }
     */
    public TransaccionPago getArg0() {
        return arg0;
    }

    /**
     * Define el valor de la propiedad arg0.
     *
     * @param value allowed object is
     *              {@link TransaccionPago }
     */
    public void setArg0(TransaccionPago value) {
        this.arg0 = value;
    }

}
