package uy.mgcoders.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by pablo on 10/22/15.
 */
@XmlType(propOrder = {"idCompra","codigo","descripcion"})
@XmlRootElement(name = "Resultado")
public class Resultado {

    private long idCompra;
    private String codigo;
    private String descripcion;

    public long getIdCompra() {
        return idCompra;
    }

    @XmlElement(required = true)
    public void setIdCompra(long idCompra) {
        this.idCompra = idCompra;
    }

    public String getCodigo() {
        return codigo;
    }

    @XmlElement(required = true)
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @XmlElement
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
