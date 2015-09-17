
package otherservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Clase Java para confirmacionTransaccion complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="confirmacionTransaccion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confirmacion" type="{http://services.middleware.fing.org/}confirmacionPago" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmacionTransaccion", propOrder = {
        "confirmacion"
})
public class ConfirmacionTransaccion {

    @XmlElement(nillable = true)
    protected List<ConfirmacionPago> confirmacion;

    /**
     * Gets the value of the confirmacion property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the confirmacion property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfirmacion().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConfirmacionPago }
     */
    public List<ConfirmacionPago> getConfirmacion() {
        if (confirmacion == null) {
            confirmacion = new ArrayList<ConfirmacionPago>();
        }
        return this.confirmacion;
    }

}
