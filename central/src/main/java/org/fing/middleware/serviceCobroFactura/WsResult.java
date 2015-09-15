
package org.fing.middleware.serviceCobroFactura;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsResult complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="wsResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCobro" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsResult", propOrder = {
        "idCobro",
        "mensaje",
        "status"
})
public class WsResult {

    protected long idCobro;
    protected String mensaje;
    protected boolean status;

    /**
     * Gets the value of the idCobro property.
     */
    public long getIdCobro() {
        return idCobro;
    }

    /**
     * Sets the value of the idCobro property.
     */
    public void setIdCobro(long value) {
        this.idCobro = value;
    }

    /**
     * Gets the value of the mensaje property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Gets the value of the status property.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     */
    public void setStatus(boolean value) {
        this.status = value;
    }

}
