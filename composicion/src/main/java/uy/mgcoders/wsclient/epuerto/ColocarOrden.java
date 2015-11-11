
package uy.mgcoders.wsclient.epuerto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for colocarOrden complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="colocarOrden">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificadorCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificadorProducto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "colocarOrden", propOrder = {
    "identificadorCompra",
    "identificadorProducto",
    "cantidad"
})
public class ColocarOrden {

    protected String identificadorCompra;
    protected long identificadorProducto;
    protected int cantidad;

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
     * Gets the value of the identificadorProducto property.
     * 
     */
    public long getIdentificadorProducto() {
        return identificadorProducto;
    }

    /**
     * Sets the value of the identificadorProducto property.
     * 
     */
    public void setIdentificadorProducto(long value) {
        this.identificadorProducto = value;
    }

    /**
     * Gets the value of the cantidad property.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

}
