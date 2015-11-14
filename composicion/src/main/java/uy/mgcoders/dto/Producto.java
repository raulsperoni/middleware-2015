package uy.mgcoders.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by pablo on 10/22/15.
 */
@XmlType(propOrder = {"idProducto","cantidad","precioUnitario"})
@XmlRootElement(name = "Producto")
public class Producto {

    private long idProducto;
    private int cantidad;
    private double precioUnitario;

    public long getIdProducto() {
        return idProducto;
    }

    @XmlElement(required = true)
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    @XmlElement(required = true)
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    @XmlElement(required = true)
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
