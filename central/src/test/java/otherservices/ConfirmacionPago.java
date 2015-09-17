
package otherservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para confirmacionPago complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="confirmacionPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificadorPago" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="resultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmacionPago", propOrder = {
        "descripcion",
        "identificadorPago",
        "resultado"
})
public class ConfirmacionPago {

    protected String descripcion;
    protected long identificadorPago;
    protected String resultado;

    /**
     * Obtiene el valor de la propiedad descripcion.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad identificadorPago.
     */
    public long getIdentificadorPago() {
        return identificadorPago;
    }

    /**
     * Define el valor de la propiedad identificadorPago.
     */
    public void setIdentificadorPago(long value) {
        this.identificadorPago = value;
    }

    /**
     * Obtiene el valor de la propiedad resultado.
     *
     * @return possible object is
     * {@link String }
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Define el valor de la propiedad resultado.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setResultado(String value) {
        this.resultado = value;
    }

}
