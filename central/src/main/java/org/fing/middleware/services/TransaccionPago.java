
package org.fing.middleware.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para transaccionPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="transaccionPago"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fechaCobro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="formaPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificadorCliente" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="numeroSucursal" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="pagos" type="{http://services.middleware.fing.org/}pago" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transaccionPago", propOrder = {
    "fechaCobro",
    "formaPago",
    "identificadorCliente",
    "numeroSucursal",
    "pagos"
})
public class TransaccionPago {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCobro;
    protected String formaPago;
    protected long identificadorCliente;
    protected long numeroSucursal;
    @XmlElement(nillable = true)
    protected List<Pago> pagos;

    /**
     * Obtiene el valor de la propiedad fechaCobro.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCobro() {
        return fechaCobro;
    }

    /**
     * Define el valor de la propiedad fechaCobro.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCobro(XMLGregorianCalendar value) {
        this.fechaCobro = value;
    }

    /**
     * Obtiene el valor de la propiedad formaPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * Define el valor de la propiedad formaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaPago(String value) {
        this.formaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad identificadorCliente.
     * 
     */
    public long getIdentificadorCliente() {
        return identificadorCliente;
    }

    /**
     * Define el valor de la propiedad identificadorCliente.
     * 
     */
    public void setIdentificadorCliente(long value) {
        this.identificadorCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroSucursal.
     * 
     */
    public long getNumeroSucursal() {
        return numeroSucursal;
    }

    /**
     * Define el valor de la propiedad numeroSucursal.
     * 
     */
    public void setNumeroSucursal(long value) {
        this.numeroSucursal = value;
    }

    /**
     * Gets the value of the pagos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pagos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPagos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Pago }
     * 
     * 
     */
    public List<Pago> getPagos() {
        if (pagos == null) {
            pagos = new ArrayList<Pago>();
        }
        return this.pagos;
    }

}
