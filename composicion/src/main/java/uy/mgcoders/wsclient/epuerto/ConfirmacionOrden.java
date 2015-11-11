
package uy.mgcoders.wsclient.epuerto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for confirmacionOrden complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="confirmacionOrden">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificadorCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificadorReserva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoResultado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descripcionResultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmacionOrden", propOrder = {
    "identificadorCompra",
    "identificadorReserva",
    "codigoResultado",
    "descripcionResultado"
})
public class ConfirmacionOrden {

    protected String identificadorCompra;
    protected String identificadorReserva;
    protected int codigoResultado;
    protected String descripcionResultado;

    /**
     * Gets the value of the identificadorCompra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorCompra() {
        return identificadorCompra;
    }

    /**
     * Sets the value of the identificadorCompra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorCompra(String value) {
        this.identificadorCompra = value;
    }

    /**
     * Gets the value of the identificadorReserva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorReserva() {
        return identificadorReserva;
    }

    /**
     * Sets the value of the identificadorReserva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorReserva(String value) {
        this.identificadorReserva = value;
    }

    /**
     * Gets the value of the codigoResultado property.
     * 
     */
    public int getCodigoResultado() {
        return codigoResultado;
    }

    /**
     * Sets the value of the codigoResultado property.
     * 
     */
    public void setCodigoResultado(int value) {
        this.codigoResultado = value;
    }

    /**
     * Gets the value of the descripcionResultado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionResultado() {
        return descripcionResultado;
    }

    /**
     * Sets the value of the descripcionResultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionResultado(String value) {
        this.descripcionResultado = value;
    }

}
