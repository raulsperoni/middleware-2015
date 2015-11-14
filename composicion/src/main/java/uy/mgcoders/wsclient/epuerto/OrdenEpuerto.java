
package uy.mgcoders.wsclient.epuerto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ordenEpuerto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ordenEpuerto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lineas" type="{http://services.mgcoders.uy/}lineas" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ordenEpuerto", propOrder = {
    "lineas"
})
public class OrdenEpuerto {

    @XmlElement(nillable = true)
    protected List<Lineas> lineas;

    /**
     * Gets the value of the lineas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Lineas }
     * 
     * 
     */
    public List<Lineas> getLineas() {
        if (lineas == null) {
            lineas = new ArrayList<Lineas>();
        }
        return this.lineas;
    }

}
