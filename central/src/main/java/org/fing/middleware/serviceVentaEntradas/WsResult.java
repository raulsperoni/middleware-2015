
package org.fing.middleware.serviceVentaEntradas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para wsResult complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="wsResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCobro" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lCodigosEntrada" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsResult", propOrder = {
    "idCobro",
    "mensaje",
    "status",
    "lCodigosEntrada"
})
public class WsResult {

    protected long idCobro;
    protected String mensaje;
    protected boolean status;
    @XmlElement(nillable = true)
    protected List<String> lCodigosEntrada;

    /**
     * Obtiene el valor de la propiedad idCobro.
     * 
     */
    public long getIdCobro() {
        return idCobro;
    }

    /**
     * Define el valor de la propiedad idCobro.
     * 
     */
    public void setIdCobro(long value) {
        this.idCobro = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     */
    public void setStatus(boolean value) {
        this.status = value;
    }

    /**
     * Gets the value of the lCodigosEntrada property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lCodigosEntrada property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLCodigosEntrada().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLCodigosEntrada() {
        if (lCodigosEntrada == null) {
            lCodigosEntrada = new ArrayList<String>();
        }
        return this.lCodigosEntrada;
    }

}
