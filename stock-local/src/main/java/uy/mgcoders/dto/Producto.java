package uy.mgcoders.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by pablo on 10/21/15.
 */
@XmlType(propOrder = {"idProducto", "cantidad"})
public class Producto {

    private long idProducto;
    private int cantidad;


    public long getIdProducto() {
        return idProducto;
    }

    @XmlElement
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    @XmlElement
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
